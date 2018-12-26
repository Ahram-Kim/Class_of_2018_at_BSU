; scheme --load retand.scm < /dev/null

(define (f a b c)
  (and a
       b
       c
       "foo"))

(display (f #t #t #t))
(display "\n")
