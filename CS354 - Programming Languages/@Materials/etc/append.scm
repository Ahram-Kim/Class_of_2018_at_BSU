; scheme --load append.scm < /dev/null

; Both s and t are assumed to be lists.

(define (append s t)
  (if (pair? s)
      (cons (car s) (append (cdr s) t))
      t))

(pp (append '() '(4 5 6)))
(pp (append '(1 2 3) '()))
(pp (append '(1 2 3) '(4 5 6)))
