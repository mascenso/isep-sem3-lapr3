.section .data

.section .text
.global sens_temp       # char sens_temp(char ult_temp, char comp_rand);
.global sens_velc_vento # unsigned char sens_velc_vento(unsigned char ult_velc_vento, char comp_rand);
.global sens_dir_vento  # unsigned short sens_dir_vento(unsigned short ult_dir_vento, short comp_rand);
.global sens_pluvio     # unsigned char sens_pluvio(unsigned char ult_pluvio, char ult_temp, char comp_rand);

.equ TEMP_DELTA, 2
.equ WINDVEL_DELTA, 44
.equ WINDDIR_DELTA, 20
.equ PLUVIO_DELTA, 20

.equ LIMIT_TEMP_BAIXA, 7
.equ LIMIT_TEMP_MEDIA, 16
.equ LIMIT_TEMP_ALTA, 100

	
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
         movl $TEMP_DELTA, %esi
             call pcg32_random_r_min_max # int32_t pcg32_random_r_min_max(int min, int max)
     popq %rdi
     popq %rsi

    # random number between 0 and TEMP_DELTA is in %eax
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



sens_velc_vento:            # unsigned char sens_velc_vento(unsigned char ult_velc_vento, char comp_rand);
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
         movl $WINDVEL_DELTA, %esi
             call pcg32_random_r_min_max # int32_t pcg32_random_r_min_max(int min, int max)
     popq %rdi
     popq %rsi

    # random number between 0 and WINDVEL_DELTA is in %eax
    # ult_velc_vento is in %dil
    addb %dil, %al
    # always positive
    andb $0x7f, %al


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

sens_dir_vento:  # unsigned short sens_dir_vento(unsigned short ult_dir_vento, short comp_rand);
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
         movl $WINDDIR_DELTA, %esi
             call pcg32_random_r_min_max # int32_t pcg32_random_r_min_max(int min, int max)
     popq %rdi
     popq %rsi

    # random number between 0 and WINDDIR_DELTA is in %eax
    # ult_velc_vento is in %dil
    addb %dil, %al
    # always positive
    andb $0x7f, %al


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


sens_pluvio:     # unsigned char sens_pluvio(unsigned char ult_pluvio, char ult_temp, char comp_rand);
#ult_pluvio in sil
#ult_temp in dil
#comp_rand in rdl


    #--#- prologue -#--#
    pushq %rbp
    movq %rsp, %rbp
    #	#
    pushq %rbx
    pushq %rsi
    pushq %rdi
    pushq %rcx
    #--#--#--#--#--#--#
 # Quando a pluviosidade anterior for nula, se o valor de modificação for negativo a
 # pluviosidade deverá permanecer nula.

    cmpb $0, %sil
    jne .continue
    movb $0, %al
    jmp .end

    .continue:

     pushq %rsi
     pushq %rdi

         movl $0, %edi
         movl $PLUVIO_DELTA, %esi
             call pcg32_random_r_min_max # int32_t pcg32_random_r_min_max(int min, int max)
     popq %rdi
     popq %rsi

    # random number between 0 and PLUVIO_DELTA is in %eax
    # ult_temp is in %dil
    # ult_pluvio is in %sil

    # if ult_temp < LIMIT_TEMP_MEDIA
    cmpb $LIMIT_TEMP_MEDIA, %dil
    jl .baixa

    # shift right 1 bit
    shr $1, %al

    .baixa:
    addb %dil, %al

    cmpb $0, %al
    jg .end
    movb $0, %al

    .end:
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
