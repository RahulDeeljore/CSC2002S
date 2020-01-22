.data			

prompt:	.asciiz	"Enter a series of 5 formulae:\n"
word1:	.space 20	
word2:	.space 20
word3:	.space 20
word4:	.space 20
word5:	.space 20
words:	.space 20
message:	.asciiz "The values are:\n"
.text			
	
main:			

li	$v0,4 		
la	$a0,prompt	 	
syscall 	

la	$a0,word1	
li	$a1,20	
li 	$v0,8	
syscall		
la	$a0,word2
li	$a1,20	
syscall		
la	$a0,word3
li	$a1,20	
syscall	
la	$a0,word4
li	$a1,20		
syscall		
la	$a0,word5	
li	$a1,20	
syscall		

li	$v0,4		
la	$t1,message		
move	$a0,$t1		
syscall			


one:
la	$t1,word1	
lb	$t0, ($t1)
bne 	$t0, '=', subs1	
addu	$t1,$t1,1	
lb	$t0, ($t1)
beq 	$t0, '1', pr12	
beq 	$t0, '2', pr13	
beq 	$t0, '3', pr14	
beq 	$t0, '4', pr15	
subs1:
move	$a0,$t1		
syscall			
j two
pr12:
la	$t3,word2	
move	$t1,$t3
move	$a0,$t3		
syscall			
j two
pr13:
la	$t3,word3	
move	$t1,$t3
move	$a0,$t3		
syscall			
j two
pr14:
la	$t3,word4	
move	$t1,$t3
move	$a0,$t3		
syscall			
j two
pr15:
la	$t3,word5	
move	$t1,$t3
move	$a0,$t3		
syscall			
j two

two:
la	$t1,word2	
lb	$t0, ($t1)
bne 	$t0, '=', subs2	
addu	$t1,$t1,1	
lb	$t0, ($t1)
beq 	$t0, '0', pr21	
beq 	$t0, '2', pr23	
beq 	$t0, '3', pr24	
beq 	$t0, '4', pr25	
subs2:
move	$a0,$t1		
syscall			
j three
pr21:
la	$t2,word1	
move	$t1,$t2
move	$a0,$t2		
syscall			
j three
pr23:
la	$t2,word3	
move	$t1,$t2
move	$a0,$t2		
syscall			
j three
pr24:
la	$t2,word4	
move	$t1,$t2
move	$a0,$t2		
syscall			
j three
pr25:
la	$t2,word5	
move	$t1,$t2
move	$a0,$t2		
syscall			
j three

three:
la	$t1,word3	
lb	$t0, ($t1)
bne 	$t0, '=', subs3	
addu	$t1,$t1,1	
lb	$t0, ($t1)
beq 	$t0, '0', pr31	
beq 	$t0, '1', pr32	
beq 	$t0, '3', pr34	
beq 	$t0, '4', pr35	
subs3:
move	$a0,$t1		
syscall			
j four
pr31:
la	$t2,word1	
move	$t1,$t2
move	$a0,$t2		
syscall			
j four
pr32:
la	$t2,word2	
lb	$t0, ($t2)
bne 	$t0, '=', subs32	
addu	$t2,$t2,1	
lb	$t0, ($t2)
beq 	$t0, '0', pr31	
beq 	$t0, '1', pr32	
beq 	$t0, '3', pr34	
beq 	$t0, '4', pr35	
move	$t1,$t2
move	$a0,$t2		
syscall			
j four
subs32:
move	$a0,$t2
syscall
j four
pr34:
la	$t2,word4	
move	$t1,$t2
move	$a0,$t2		
syscall			
j four
pr35:
la	$t2,word5	
move	$t1,$t2
move	$a0,$t2		
syscall			
j four


four:
la	$t1,word4	
lb	$t0, ($t1)
bne 	$t0, '=', subs4	
addu	$t1,$t1,1	
lb	$t0, ($t1)
beq 	$t0, '0', pr41	
beq 	$t0, '1', pr42	
beq 	$t0, '2', pr43	
beq 	$t0, '4', pr45	
subs4:
move	$a0,$t1		
syscall			
j five
pr41:
la	$t2,word1	
move	$t1,$t2
move	$a0,$t2		
syscall			
j five
pr42:

la	$t2,word2	
lb	$t0, ($t2)
bne 	$t0, '=', subs42	
addu	$t2,$t2,1	
lb	$t0, ($t2)
beq 	$t0, '0', pr41	
beq 	$t0, '3', pr43	
beq 	$t0, '4', pr45	
move	$t1,$t2
move	$a0,$t2		
syscall			
j five
subs42:
move	$a0,$t2
syscall
j five

pr43:			
la	$t2,word3	
lb	$t0, ($t2)
bne 	$t0, '=', subs43	
addu	$t2,$t2,1	
lb	$t0, ($t2)
beq 	$t0, '0', pr41	
beq 	$t0, '1', pr42	
beq 	$t0, '4', pr45	
move	$t1,$t2
move	$a0,$t2		
syscall			
j five
subs43:
move	$a0,$t2
syscall
j five

j five
pr45:
la	$t2,word5	
move	$t1,$t2
move	$a0,$t2		
syscall			
j five

five:
la	$t1,word5	
lb	$t0, ($t1)
bne 	$t0, '=', subs5	
addu	$t1,$t1,1	
lb	$t0, ($t1)
beq 	$t0, '0', pr51	
beq 	$t0, '1', pr52	
beq 	$t0, '2', pr53	
beq 	$t0, '3', pr54	
subs5:
move	$a0,$t1		
syscall			
j end

pr51:
la	$t2,word1 
move	$t1,$t2
move	$a0,$t2		
syscall			
j end

pr52:
la	$t2,word2	
lb	$t0, ($t2)
bne 	$t0, '=', subs52	
addu	$t2,$t2,1	
lb	$t0, ($t2)
beq 	$t0, '0', pr51	
beq 	$t0, '3', pr53	
beq 	$t0, '4', pr54	
move	$t1,$t2
move	$a0,$t2		
syscall			
j end
subs52:
move	$a0,$t2
syscall
j end

pr53:
la	$t2,word3	
lb	$t0, ($t2)
bne 	$t0, '=', subs53	
addu	$t2,$t2,1	
lb	$t0, ($t2)
beq 	$t0, '0', pr51	
beq 	$t0, '1', pr52	
beq 	$t0, '4', pr54	
move	$t1,$t2
move	$a0,$t2		
syscall			
j end
subs53:
move	$a0,$t2
syscall
j end

pr54:
la	$t2,word4	
lb	$t0, ($t2)
bne 	$t0, '=', subs54	
addu	$t2,$t2,1	
lb	$t0, ($t2)
beq 	$t0, '0', pr51	
beq 	$t0, '1', pr52	
beq 	$t0, '2', pr53	
move	$t1,$t2
move	$a0,$t2	
syscall		
j end
subs54:
move	$a0,$t2
syscall
j end

end:
li	$v0,10		
syscall			
