import numpy as np
import cv2
import pybase64
import io
import imutils
import cv2
from imutils.perspective import four_point_transform
from imutils import contours
import numpy as np
import scipy
from PIL import Image
from os.path import dirname, join

def imageProcess(imgdata,itemNum):
    filename = join(dirname(__file__), "../databases/Bubble.db")
    ANSWER_KEY = int(itemNum)
    show_set_name = []


    # counter = 0
    # for i in show_set_name:
    #     if i == "A":
    #         ANSWER_KEY[counter] = ANSWER_KEY.setdefault(counter, 0)
    #     if i == "B":
    #         ANSWER_KEY[counter] = ANSWER_KEY.setdefault(counter, 1)
    #     if i == "C":
    #         ANSWER_KEY[counter] = ANSWER_KEY.setdefault(counter, 2)
    #     if i == "D":
    #         ANSWER_KEY[counter] = ANSWER_KEY.setdefault(counter, 3)
    #     counter += 1
#
#
#
    correct = 0
    counter = 0
    answer_input = []
    decoded_data = pybase64.b64decode(imgdata)
    np_data = np.fromstring(decoded_data, np.uint8)
    image = cv2.imdecode(np_data, cv2.IMREAD_UNCHANGED)
    gray = cv2.cvtColor(image,cv2.COLOR_BGR2GRAY)
    blurred = cv2.GaussianBlur(gray, (3, 3), 0)
    edged = cv2.Canny(blurred, 75, 200)

    # find contours in the edge map, then initialize
    # the contour that corresponds to the document
    cnts = cv2.findContours(edged.copy(), cv2.RETR_EXTERNAL,
                            cv2.CHAIN_APPROX_SIMPLE)
    cnts = imutils.grab_contours(cnts)

    docCnt = None
    # ensure that at least one contour was found
    if len(cnts) > 0:
        # sort the contours according to their size in
        # descending order
        cnts = sorted(cnts, key=cv2.contourArea, reverse=True)

        # loop over the sorted contours
        for c in cnts:
            # approximate the contour
            peri = cv2.arcLength(c, True)
            approx = cv2.approxPolyDP(c, 0.02 * peri, True)
            # if our approximated contour has four points,
            # then we can assume we have found the paper
            if len(approx) == 4:
                docCnt = approx
                break

    # apply a four point perspective transform to both the
    # original image and grayscale image to obtain a top-down
    # birds eye view of the paper
    paper = four_point_transform(image, docCnt.reshape(4, 2))
    height = paper.shape[0]
    width = paper.shape[1]
    warped = four_point_transform(gray, docCnt.reshape(4, 2))

    warped_resize = cv2.resize(warped, (2048, 1536), interpolation=cv2.INTER_AREA)
    # paper = cv2.resize(paper, (737, 546) , interpolation=cv2.INTER_AREA)


    blur = cv2.GaussianBlur(warped, (1, 1), 0)
    thresh = cv2.threshold(blur, 0, 255, cv2.THRESH_BINARY_INV | cv2.THRESH_OTSU)[1]

    # kernel = cv2.getStructuringElement(cv2.MORPH_RECT, (20, 40))
    # dilate = cv2.dilate(thresh, kernel, iterations=1)
    #
    cnts = cv2.findContours(thresh, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
    cnts = cnts[0] if len(cnts) == 2 else cnts[1]
    cnts = sorted(cnts, key=lambda x: cv2.boundingRect(x)[0])

    aw = []
    bw = []
    cw = []
    dw = []
    cont_list = []
    let = ['a', 'b', 'c']
    h = 0
    for c in cnts:
        x, y, w, h = cv2.boundingRect(c)
        if h > 100 and w > 100:
            if h<height and w<width:
                cv2.rectangle(paper, (x, y), (x + (w), y + h), (36, 255, 12), 2)
                aw.append(x)
                bw.append(y)
                cw.append(w)
                dw.append(h)

    if ANSWER_KEY > 0:
        a = 36
        b = 24
        c = width//3
        d = height
        num25 = paper[b:b + d, a:a + c]
        num25Gray = cv2.cvtColor(num25,cv2.COLOR_BGR2GRAY)
        num25_thresh = cv2.threshold(num25Gray, 0, 255, cv2.THRESH_BINARY_INV | cv2.THRESH_OTSU)[1]
        # pil_im = Image.fromarray(num25_thresh)
        # buff = io.BytesIO()
        # pil_im.save(buff,format="PNG")
        # img_str = pybase64.b64encode(buff.getvalue())
        # return ""+str(img_str,'utf-8')


        # find contours in the thresholded image, then initialize
        # the list of contours that correspond to questions
        cnts = cv2.findContours(num25_thresh, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
        cnts = imutils.grab_contours(cnts)

        questionCnts = []

        want = ''
        # loop over the contours
        for c in cnts:
            # compute the bounding box of the contour, then use the
            # bounding box to derive the aspect ratio
            (x, y, w, h) = cv2.boundingRect(c)
            ar = w / h
            # in order to label the contour as a question, region
            # should be sufficiently wide, sufficiently tall, and
            # have an aspect ratio approximately equal to 1
            if w >=20  and h >= 20 and ar >= .9 and ar <= 1.1:
                want = 'hello'
                questionCnts.append(c)


        # sort the question contours top-to-bottom, then initialize
        # the total number of correct answers
        questionCnts = contours.sort_contours(questionCnts, method="top-to-bottom")[0]
        counts=0
        # each question has 4 possible answers, to loop over the
        # question in batches of 4
        for (q, i) in enumerate(np.arange(0, len(questionCnts), 4)):
            if counts == 4:
                counts = 0
            # sort the contours for the current question from
            # left to right, then initialize the index of the
            # bubbled answer
            cnts = contours.sort_contours(questionCnts[i:i + 4])[0]
            bubbled = None
            # loop over the sorted contours

            for (j, c) in enumerate(cnts):
                # construct a mask that reveals only the current
                # "bubble" for the question
                mask = np.zeros(thresh.shape, dtype="uint8")
                # cv2.drawContours(mask, [c], -1, 255, -1)
                # apply the mask to the thresholded image, then
                # count the number of non-zero pixels in the
                # bubble area
                mask = cv2.bitwise_and(thresh, thresh, mask=mask)
                total = cv2.countNonZero(mask)
                # if the current total has a larger number of total
                # non-zero pixels, then we are examining the currently
                # bubbled-in answer
                if bubbled is None or total > bubbled[0]:
                    bubbled = (total, j)
                    answer_input.append(counts)
            # answer_input.append(bubbled[1])
            counter += 1
            counts +=1
        return str(answer_input)


    # if len(ANSWER_KEY) > 25:
    #     a = 390
    #     b = 24
    #     c = 300
    #     d = 1300
    #     num50 = paper[b:b + d, a:a + c]
    #
    #     num50 = cv2.cvtColor(num50, cv2.COLOR_BGR2GRAY)
    #
    #     thresh = cv2.threshold(num50, 0, 255, cv2.THRESH_BINARY_INV | cv2.THRESH_OTSU)[1]
    #
    #     # find contours in the thresholded image, then initialize
    #     # the list of contours that correspond to questions
    #     cnts = cv2.findContours(thresh.copy(), cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
    #     cnts = imutils.grab_contours(cnts)
    #     questionCnts = []
    #     # loop over the contours
    #     for c in cnts:
    #         # compute the bounding box of the contour, then use the
    #         # bounding box to derive the aspect ratio
    #         (x, y, w, h) = cv2.boundingRect(c)
    #         ar = w / float(h)
    #         # in order to label the contour as a question, region
    #         # should be sufficiently wide, sufficiently tall, and
    #         # have an aspect ratio approximately equal to 1
    #         if w >= 20 and h >= 20 and ar >= 0.9 and ar <= 1.1:
    #             questionCnts.append(c)
    #     # sort the question contours top-to-bottom, then initialize
    #     # the total number of correct answers
    #     questionCnts = contours.sort_contours(questionCnts, method="top-to-bottom")[0]
    #     # each question has 5 possible answers, to loop over the
    #     # question in batches of 5
    #
    #     for (q, i) in enumerate(np.arange(0, len(questionCnts), 4)):
    #         # sort the contours for the current question from
    #         # left to right, then initialize the index of the
    #         # bubbled answer
    #         cnts = contours.sort_contours(questionCnts[i:i + 4])[0]
    #         bubbled = None
    #         # loop over the sorted contours
    #         for (j, c) in enumerate(cnts):
    #             # construct a mask that reveals only the current
    #             # "bubble" for the question
    #             mask = np.zeros(thresh.shape, dtype="uint8")
    #             cv2.drawContours(mask, [c], -1, 255, -1)
    #             # apply the mask to the thresholded image, then
    #             # count the number of non-zero pixels in the
    #             # bubble area
    #             mask = cv2.bitwise_and(thresh, thresh, mask=mask)
    #             total = cv2.countNonZero(mask)
    #             # if the current total has a larger number of total
    #             # non-zero pixels, then we are examining the currently
    #             # bubbled-in answer
    #             if bubbled is None or total > bubbled[0]:
    #                 bubbled = (total, j)
    #
    #         # initialize the contour color and the index of the
    #         # *correct* answer
    #         # color = (0, 0, 255)
    #         # n = ANSWER_KEY[counter]
    #         answer_input.append(bubbled[1])
    #         # # check to see if the bubbled answer is correct
    #         # if n == bubbled[1]:
    #         #     color = (0, 255, 0)
    #         #     correct += 1
    #         counter += 1

    # if len(ANSWER_KEY) > 50:
    #     a = 750
    #     b = 24
    #     c = 300
    #     d = 1300
    #     num75 = paper[b:b + d, a:a + c]
    #
    #     num75 = cv2.cvtColor(num75, cv2.COLOR_BGR2GRAY)
    #
    #     thresh = cv2.threshold(num75, 0, 255, cv2.THRESH_BINARY_INV | cv2.THRESH_OTSU)[1]
    #
    #     # find contours in the thresholded image, then initialize
    #     # the list of contours that correspond to questions
    #     cnts = cv2.findContours(thresh.copy(), cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
    #     cnts = imutils.grab_contours(cnts)
    #     questionCnts = []
    #     # loop over the contours
    #     for c in cnts:
    #         # compute the bounding box of the contour, then use the
    #         # bounding box to derive the aspect ratio
    #         (x, y, w, h) = cv2.boundingRect(c)
    #         ar = w / float(h)
    #         # in order to label the contour as a question, region
    #         # should be sufficiently wide, sufficiently tall, and
    #         # have an aspect ratio approximately equal to 1
    #         if w >= 20 and h >= 20 and ar >= 0.9 and ar <= 1.1:
    #             questionCnts.append(c)
    #     # sort the question contours top-to-bottom, then initialize
    #     # the total number of correct answers
    #     questionCnts = contours.sort_contours(questionCnts, method="top-to-bottom")[0]
    #     # each question has 5 possible answers, to loop over the
    #     # question in batches of 5
    #
    #     for (q, i) in enumerate(np.arange(0, len(questionCnts), 4)):
    #         # sort the contours for the current question from
    #         # left to right, then initialize the index of the
    #         # bubbled answer
    #         cnts = contours.sort_contours(questionCnts[i:i + 4])[0]
    #         bubbled = None
    #         # loop over the sorted contours
    #         for (j, c) in enumerate(cnts):
    #             # construct a mask that reveals only the current
    #             # "bubble" for the question
    #             mask = np.zeros(thresh.shape, dtype="uint8")
    #             cv2.drawContours(mask, [c], -1, 255, -1)
    #             # apply the mask to the thresholded image, then
    #             # count the number of non-zero pixels in the
    #             # bubble area
    #             mask = cv2.bitwise_and(thresh, thresh, mask=mask)
    #             total = cv2.countNonZero(mask)
    #             # if the current total has a larger number of total
    #             # non-zero pixels, then we are examining the currently
    #             # bubbled-in answer
    #             if bubbled is None or total > bubbled[0]:
    #                 bubbled = (total, j)
    #
    #         # initialize the contour color and the index of the
    #         # *correct* answer
    #         # color = (0, 0, 255)
    #         # n = ANSWER_KEY[counter]
    #         answer_input.append(bubbled[1])
    #         # check to see if the bubbled answer is correct
    #         # if n == bubbled[1]:
    #         #     color = (0, 255, 0)
    #         #     correct += 1
    #         counter += 1
    #
    # mystring = ','.join(map(str,answer_input))

#         key = []
#         for i in answer_input:
#             if i == 0:
#                 key.append("A")
#             if i == 1:
#                 key.append("A")
#             if i == 2:
#                 key.append("A")
#             if i == 3:
#                 key.append("A")
#             counter += 1

    # return show_set_name

