#!/bin/gprolog --consult-file

:- include('data.pl').
:- include('uniq.pl').

% Your code goes here.

people([ann,bob,carla]).

lte(time(_, _, am), time(_, _, pm)).
lte(time(H1, _, P), time(H2, _, P)) :- H1 =< H2.
lte(time(H, M1, P), time(H, M2, P)) :- M1 =< M2.

common(slot(Z, W), slot(X, Y), slot(Z, W)) :-
	lte(X, Z), lte(Y, W).
common(slot(X, W), slot(X, Y), slot(Z, W)) :-
	lte(Z, X), lte(W, Y), lte(X, W), W\==X.
common(slot(Z, Y), slot(X, Y), slot(Z, W)) :-
	lte(X, Z), lte(Y, W), lte(Z, Y), Z\==Y.

commonTime(C, C, []).
commonTime(R, C, [P|T]) :-
	free(P, S), common(0, C, S), commonTime(R, 0, T).
	
meetTime(_, []).
meetTime(C, [P|T]) :- 
	free(P, S), commonTime(C, S, T).
	
meet(Slot) :-
	people(people), meetTime(Slot, people).


main :- findall(Slot, meet(Slot), Slots),
        uniq(Slots, Uniq),
        write(Uniq), nl, halt.

:- initialization(main).
