.section .data

.section .text
.global sens_temp     # char sens_temp(char ult_temp, char comp_rand);
	
sens_temp:            # char sens_temp(char ult_temp, char comp_rand);
#ult_temp in sil
#comp_rand in dil

#--#- prologue -#--#
pushq %rbp
movq %rsp, %rbp
#	#
pushq %rbx
pushq %rsi
pushq %rdi
pushq %rcx
#--#--#--#--#--#--#

     pushq %rsi
     pushq %rdi

         movl $0, %edi
         movl $2, %esi
             call pcg32_random_r_min_max # int32_t pcg32_random_r_min_max(int min, int max)
     popq %rdi
     popq %rsi

    # random int number between 0 and 5 is in %eax
    # ult_temp is in %dil
    addb %dil, %al

#--#- epilogue -#--#
popq %rcx
	popq %rdi
	popq %rsi
	popq %rbx
##	#
movq %rbp, %rsp
	popq %rbp
#--#--#--#--#--#--#
	ret
