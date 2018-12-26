/**
 * StocksController
 *
 * @description :: Server-side logic for managing stocks
 * @help        :: See http://sailsjs.org/#!/documentation/concepts/Controllers
 */

module.exports = {
	'getStocks': function(request, response){

		var jsonResponse = {"symbol":"AAPL","date":"2018-01-31","open":166.87,"high":168.4417,"low":166.5,"close":167.43,"volume":32478930,"unadjustedVolume":32478930,"change":0.46,"changePercent":0.275,"vwap":167.345};
		jsonResponse.symbol = "MSFT";

		return response.json(jsonResponse);
	},

	'loadSomething': function(request, response){

		var jsonResponse = {
			"date": "today",
			"name": "mike",
			"field": "some data file here",
			"number": 123.2
		};

		return response.json(jsonResponse);
	}
};

