.text

main:
#print promt
li	$v0,4 		
la	$a0,prompt	 	
syscall 		

# store word
la	$a0,word	
li	$a1,20		
li	$t2,0		
li 	$v0,8		
syscall			

#print message
li	$v0,4		
la	$a0,message			
syscall			

#skips 1st
li	$v0,1		
la	$t1,word	
addi	$t1,$t1,1
	

change:
lb	$t0, ($t1)	
addu	$t1,$t1,1	
beq 	$t0, 10, addition
mul	$t2,$t2,10	
sub	$t0,$t0,'0'	
add	$t2,$t2,$t0	
j change	
			
addition:
addi	$t2,$t2,5	
move	$a0,$t2		
syscall	

li	$v0,10
syscall

.data
prompt:	.asciiz	"Enter a string:\n"
word:	.space 20 
message: .asciiz "The value +5 is:\n"