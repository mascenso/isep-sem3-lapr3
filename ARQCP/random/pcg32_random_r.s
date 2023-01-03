.section .data

     .global state
     .global inc
     .equ BIGNUM, 6364136223846793005

.section .text

    .global pcg32_random_r
    .global pcg32_random_r_init
    .global pcg32_random_r_min_max

pcg32_random_r:
#The registers are used in a specified order, #with the changes used for a register
#depending on the size of the data type being #passed
#di/si/dx/cx/r8/r9

#--#- prologue -#--#
#    #pushq %rbp
#    movl %esp, %ebp
#    #
#    pushq %rbx
#    pushq %rsi
#    pushq %rdi
#--#--#--#--#--#--#

# uint64_t oldstate = state;
#    // Advance internal state
#   state = oldstate * 6364136223846793005ULL + (inc|1);
#    // Calculate output function (XSH RR), uses old state for max ILP
#  uint32_t xorshifted = ((oldstate >> 18u) ^ oldstate) >> 27u;
#  uint32_t rot = oldstate >> 59u;
#  return (xorshifted >> rot) | (xorshifted << ((-rot) & 31));


    pushq %rbp
    movq %rsp, %rbp

# uint64_t oldstate = state;

    movq state(%rip), %rax

    movq %rax, -8(%rbp)
    movq -8(%rbp), %rax

# state = oldstate * 6364136223846793005ULL + (inc|1);

    movabsq $BIGNUM, %rdx
    imulq %rax, %rdx
    movq inc(%rip), %rax
    orq $1, %rax

    addq %rdx, %rax

#  uint32_t xorshifted = ((oldstate >> 18u) ^ oldstate) >> 27u;

    movq %rax, state(%rip)

    movq -8(%rbp), %rax

    shrq $18, %rax

    xorq -8(%rbp), %rax

    shrq $27, %rax

    ######################################################

#  uint32_t rot = oldstate >> 59u;

    movl %eax, -16(%rbp)

    shrq $59, %rax

#  return (xorshifted >> rot) | (xorshifted << ((-rot) & 31));

    movl %eax, -12(%rbp)
    movl -12(%rbp), %eax

    movl -16(%rbp), %edx

    movl %eax, %ecx

    rorl %cl, %edx

    movl %edx, %eax


#--#- epilogue -#--#
#    popq %rdi
#    popq %rsi
#    popq %rbx
#    #
#    movl %ebp, %esp
#    popq %rbp
#--#--#--#--#--#--#

end:

    movq %rbp, %rsp
    popq %rbp
    ret

pcg32_random_r_init:

//   pushq %rbp
//   pushq %rbx
//       call random
//       movl %eax, state(%rip)
//   popq %rbx
//   popq %rbp
//
//   pushq %rbp
//   pushq %rbx
//      call random
//      movl %eax, inc(%rip)
//
//      popq %rbx
//      popq %rbp

    movq $32, %rcx
    .loop:
        pushq %rcx
        call pcg32_random_r
        popq %rcx
    loop .loop

ret


pcg32_random_r_min_max:

# return ( pcg32_random_r_c_init() % (max - min + 1) ) + min;
# %max is in %edi
# %min is in %esi

# dividend : % eax
    pushq %rdi
    pushq %rsi
    pushq %rcx
    call pcg32_random_r_init # %eax has the 32 bit random generated number
    popq %rcx
    popq %rsi
    popq %rdi

# converts the signed long in % eax to the signed double long in % edx :% eax
    cltd

# divisor : edi
    subl %esi, %edi # %edi = %max - %min
    addl $1, %edi # %edi = %max - %min + 1

# divides % edx :% eax by % edi ( remainder in % edx , quotient in % eax )
    idivl % edi
    addl $1, %edx
    movl $0, %eax
    movl %edx, %eax

    ret




