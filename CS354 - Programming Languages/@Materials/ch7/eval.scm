; scheme --load eval.scm < /dev/null

(let ((prog (cons 'display (cons "Hi!" '()))))
  (display "\n")
  (display prog)
  (display "\n")
  (eval prog user-initial-environment)
  (display "\n"))
