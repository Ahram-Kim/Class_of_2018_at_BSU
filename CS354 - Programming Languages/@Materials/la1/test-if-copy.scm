; scheme --load test-if-copy.scm < /dev/null

(load "replace.scm")

(define source    '(a (b c) d))
(define search-for   '(b c))
(define replace-with '(x y))

(define result (replace source search-for replace-with))
(display result)
(display "\n")

(set-car! replace-with 'side-effect)	; imperative assignment: ugh!
(display result)
(display "\n")

