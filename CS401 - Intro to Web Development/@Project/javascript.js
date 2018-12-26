           
            $(document).ready(function() {

                // subheader jquery
                $("#1").click(function() {
                    $("#1").animate({
                        height: '+=150px', width: '+=150px'
                    });
                });
                $("#2").click(function() {
                    $("#2").animate({
                        height: '+=150px', width: '+=150px'
                    });
                });
                $("#3").click(function() {
                    $("#3").animate({
                        height: '+=150px', width: '+=150px'
                    });
                });
                $("#4").click(function() {
                    $("#4").animate({
                        height: '+=150px', width: '+=150px'
                    });
                });
                $("#5").click(function() {
                    $("#5").animate({
                        height: '+=150px', width: '+=150px'
                    });
                });

                // input jquery
                $("input").focus(function(){
                    $(this).css("background-color", "#cccccc");
                });
                $("input").blur(function(){
                    $(this).css("background-color", "#ffffff");
                });
            });

