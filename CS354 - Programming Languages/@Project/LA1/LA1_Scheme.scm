;scheme super-duper 

(define (super-duper source count)
  (cond ((zero? count) '())
	((not (pair? source)) source)
        (else (cons (duper (car source) count)
                    (super-duper (cdr source) count)))))

(define (duper atom count2)
  (cond ((zero? count2) '())
        ((pair? atom)
         (duperList (cons (duper (car atom) count2)
                          (super-duper ((cdr atom) count2)) count2)))
        (else (cons atom
                    (duper atom (- count2 1))))))

(define (duperList sourceList count3)
  (cond ((zero? count3) '())
        (else (cons sourceList
                    (duperList sourceList (- count3 1))))))
