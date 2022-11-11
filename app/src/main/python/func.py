import sqlite3
import os.path
from fpdf import FPDF


def createPdf(subTxt, techTxt):
    package_dir = os.path.abspath(os.path.dirname(__file__))
    db_dir = os.path.join(package_dir, 'Bubble.db')

    con = sqlite3.connect(db_dir)
    con.row_factory = sqlite3.Row
    c = con.cursor()
    c.execute(f"select * from questionnaireTbl")
    data_show = [dict(row) for row in c.fetchall()]
    print(data_show)
    c.close()
    con.close()

    pdf = FPDF('P', 'mm', 'A4')
    pdf.set_auto_page_break(True)
    pdf.add_page()
    pdf.rect(5, 5, 200, 287, 'D')
    num = 2
    cho = 4
    name = []
    choice = []
    quest = 1
    letter = ["a. ", "b. ", "c. ", "d. "]
    for i in range(len(data_show)):
        name.append(data_show[i])
        choice.append(data_show[i+1])
        choice.append(data_show[i + 2])
        choice.append(data_show[i + 3])
        choice.append(data_show[i + 4])
    pdf.set_font('times', '', 16)
    pdf.cell(55, 10, "Subject:", border=True)
    pdf.cell(135, 10, subTxt, border=True)
    pdf.cell(0, 10, "", new_x="LMARGIN", new_y="NEXT")
    pdf.cell(55, 10, "Teacher:", border=True)
    pdf.cell(135, 10, techTxt, border=True)
    pdf.cell(0, 10, "", new_x="LMARGIN", new_y="NEXT")
    pdf.cell(0, 10, "", new_x="LMARGIN", new_y="NEXT")
    counter = 0
    letter_num = 0
    for i in name:
        pdf.multi_cell(0, 10, str(quest) + ". " + i, new_x="LMARGIN", new_y="NEXT")
        while counter % 4 < len(choice):
            pdf.cell(95, 10, letter[letter_num] + choice[counter])
            pdf.cell(95, 10, letter[letter_num + 1] + choice[counter + 1])
            pdf.cell(95, 10, "", new_x="LMARGIN", new_y="NEXT")
            counter += 2
            letter_num += 2
            if counter % 4 == 0:
                break
        letter_num = 0
        quest += 1
        pdf.rect(5, 5, 200, 287, 'D')
    pdf.output(D, '/download/questionnaire.pdf')
