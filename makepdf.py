# -*- coding: utf-8 -*- 
import os
import re

def write_header(output):
	output.write("\\documentclass{article}\n\\usepackage[top=0.5in, bottom=0.5in, left=0.5in, right=0.5in]{geometry}\n\\usepackage{xcolor}\n\\usepackage{multicol}\n\\usepackage{listings}\n\\lstset{language=Java, escapeinside=``, breaklines=true, frame=none,extendedchars=false, keywordstyle=\\color{blue!70}\\bfseries, basicstyle=\\ttfamily\\small, commentstyle=\\ttfamily\\color{green!40!black}, showstringspaces=false}\n\\usepackage{color}\n\\usepackage{hyperref}\n\\hypersetup{colorlinks=true, linkcolor=blue}\n\\begin{document}\n\\begin{multicols}{2}\n\\tableofcontents\n")

def write_tail(output):
	output.write("\n\\end{multicols}\n\\end{document}")

def generate():
	if (os.path.exists(path)):
		pass
	else:
		print 'No problems solutions found. Run lc_crawler_tag.py first.'
		return
	tag_file = open('tags.txt', 'r')
	output = open('lc_crawler_pdf.tex', 'w')
	write_header(output)
	finished = []
	tot_cnt = 0
	for each_tag in tag_file:
		each_tag = each_tag.strip('\n').strip()
		ls = os.listdir(path + each_tag)
		output.write('\\section{%s}\n' % each_tag)
		print 'Tag: %s' % each_tag
		output.write('Tag: %s\n' % each_tag)
		for each_file in ls:
			if (each_file.endswith('_code.txt')):
				continue
			else:
				tot_cnt = tot_cnt + 1
				problem_name = each_file[:-16]
				if problem_name in finished:	
					output.write('\\subsection{%s++}\n' % problem_name)
					print '\t%s\t[PASS]\t\t%s' % (tot_cnt, problem_name)
					continue
				else:
					finished.append(problem_name)

				try:
					description_file = open(path + each_tag + '/' + each_file, 'r')
					code_file = open(path + each_tag + '/' + problem_name + '_code.txt', 'r')
					# write description in file
					output.write('\\subsection{%s}\n' % problem_name)
					output.write('\\begin{lstlisting}\n')
					output.write(description_file.read())
					output.write(code_file.read())
					output.write('\\end{lstlisting}\n')
					print '\t%s\t[OK]\t\t%s' % (tot_cnt, problem_name)
				except Exception as e:
					print '\t%s\t[Failed]\t%s' % (tot_cnt, problem_name)
	tag_file.close()
	write_tail(output)
	output.close()
	print 'Done'

if __name__ == '__main__':
	global path
	path = 'Answer/'
	generate()