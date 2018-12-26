; scheme --load closure2.scm < /dev/null

(define fff 0)				;"global" variable: ugh!

(define (g ff)
  (set! fff ff))			;assignment "statement": ugh!

(define (closure)
  (let ((i 1))				;early/deep
    (define (f)
      (display i)
      (display "\n"))
    (g f)))

(closure)
(let ((i 2))				;late/shallow
  (fff))
(fff)
