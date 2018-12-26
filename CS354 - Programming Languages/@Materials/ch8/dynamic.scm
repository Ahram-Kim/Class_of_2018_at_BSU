; scheme --load dynamic.scm < /dev/null

(define (main)
  (let ((i 1))
    (define (f)
      (display i)
      (display "\n"))
    (f)
    (let ((i 2))
      (f))
    (fluid-let ((i 2))
      (f))
    (f)))

(main)


