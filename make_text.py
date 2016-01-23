# -*- coding: utf-8 -*- 
import os
import re

def generate():
	if (os.path.exists(path)):
		pass
	else:
		print 'No problems solutions found. Run lc_crawler_tag.py first.'
		return

	tag_file = open('tags.txt', 'r')
	output = open('output.txt', 'w')
	finished = []
	tot_cnt = 0
	for each_tag in tag_file:
		each_tag = each_tag.strip('\n').strip()
		ls = os.listdir(path + each_tag)
		print 'Tag: %s' % each_tag
		output.write('++++++++++++Tag: %s++++++++++++\n' % each_tag)
		for each_file in ls:
			if (each_file.endswith('_code.txt')):
				continue
			else:
				tot_cnt = tot_cnt + 1
				problem_name = each_file[:-16]
				if problem_name in finished:	
					output.write(problem_name + '++\n')
					print '\t%s\t[PASS]\t\t%s' % (tot_cnt, problem_name)
					continue
				else:
					finished.append(problem_name)

				try:
					description_file = open(path + each_tag + '/' + each_file, 'r')
					code_file = open(path + each_tag + '/' + problem_name + '_code.txt', 'r')
					output.write('\n===============' + problem_name + '===============\n')
					output.write(description_file.read())
					output.write(code_file.read())
					print '\t%s\t[OK]\t\t%s' % (tot_cnt, problem_name)
				except Exception as e:
					print '\t%s\t[Failed]\t%s' % (tot_cnt, problem_name)
	tag_file.close()
	output.close()
	print 'done'

if __name__ == '__main__':
	global path
	path = 'Answer/'
	generate()