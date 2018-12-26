#!/bin/gprolog --consult-file

:- include('data.pl').

% Your code goes here.

lte(time(_, _, am), time(_, _, pm)).
lte(time(H1, _, P), time(H2, _, P)) :- H1 =< H2.
lte(time(H, M1, P), time(H, M2, P)) :- M1 =< M2.


meetone(Person, slot(meetBT, meetET)) :-
	free(Person, freeBT, freeET),
	lte(freeBT,meetBT), lte(meetET, freeET).

	
main :- findall(Person,
		meetone(Person,slot(time(8,30,am),time(8,45,am))),
		People),
	write(People), nl, halt.

:- initialization(main).
