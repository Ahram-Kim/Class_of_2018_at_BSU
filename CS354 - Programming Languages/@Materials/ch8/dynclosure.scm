; scheme --load dynclosure.scm < /dev/null

(define (g f)
    (f))

(define (main)
  (let ((i 1))				; early/deep
    (define (f)
      (display i)
      (display "\n"))
    (fluid-let ((i 2))			; late/shallow
      (g f))))

(main)
