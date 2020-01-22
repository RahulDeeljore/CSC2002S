.text

main:
    # prompt the user input
    li $v0, 4
    la $a0, prompt
    syscall

    # get input
    li $v0, 8
    la $a0, word1
    li $a1, 20
    syscall
   

    # get input
    li $v0, 8
    la $a0, word2
    li $a1, 20
    syscall

    # get input
    li $v0, 8
    la $a0, word3
    li $a1, 20
    syscall


    # get input
    li $v0, 8
    la $a0, word4
    li $a1, 20
    syscall

    # get input
    li $v0, 8
    la $a0, word5
    li $a1, 20
    syscall

    # store the result in $t0
    # move $t0, word1

    # Display message
    li $v0, 4
    la $a0, message
    syscall

    # Print numbers
    li $v0, 4
    la $a0, word1
    syscall

    # Print numbers
    li $v0, 4
    la $a0, word2
    syscall

    # Print numbers
    li $v0, 4
    la $a0, word3
    syscall

    # Print numbers
    li $v0, 4
    la $a0, word4
    syscall

    # Print numbers
    li $v0, 4
    la $a0, word5
    syscall

    li $v0, 10
    syscall
   

.data
     prompt: .asciiz "Enter a series of 5 formulae:\n"
     
     message: .asciiz "The values are:\n"
    
     word1: .space 6
     word2: .space 6
     word3: .space 6
     word4: .space 6
     word5: .space 6
