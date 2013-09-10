<%-- 
    Document   : index
    Created on : Sep 9, 2013, 9:31:36 PM
    Author     : jrankin2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Shape Calculator</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        
        <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/themes/dot-luv/jquery-ui.css" type="text/css" media="all" />
        
        <!--
        <meta http-equiv="Refresh" content="5"/>
        
        black-tie, blitzer, cupertino, dark-hive, dot-luv, eggplant, excite-bike, 
        flick, hot-sneaks, humanity, le-frog, mint-choc, overcast, pepper-grinder, 
        redmond, smoothness, south-street, start, sunny, swanky-purse, trontastic, 
        ui-darkness, ui-lightness, and vader.
        
        
        -->
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        
        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.js"></script>
        <style type="text/css">
            #tabs{
                width:450px;
                min-width:450px;
                height:440px;
                min-height:440px;
                
                overflow:hidden;
                overflow-style:scrollbar;
                font-size:15px;
            }
            
            input.error{
                border:1px solid red;
            }
            
            label.error{
                color:red;
                float:left;
                clear:left;
                width:97%;
            }
            
            input[type="text"]{
                margin-bottom:5px;
                border-radius:5px;
                padding-left:5px;
                width:70px;
            }
            
            #triangleForm li:first-child{
                list-style-type: none;
            }
            
        </style>
    </head>
    <body>
        <div id="tabs">
            <ul>
                <li><a href="#tab-1">Rectangle</a></li>
                <li><a href="#tab-2">Circle</a></li>
                <li><a href="#tab-3">Triangle</a></li>
            </ul>

            <div id="tab-1">
                <form id="rectangleForm" action="ShapeCalculator" method="GET">
                    <input type="hidden" name="s" value="0"/>
                    Calculate the area of a rectangle by providing the:
                    <ul>
                        <li>Length: <input name="length" type="text" value="${length}" placeholder="Length" required /></li>
                        <li>Width: <input name="width" type="text" value="${width}" placeholder="Width" required /></li>
                    </ul>
                    <input type="submit" value="Calculate"/>
                </form>
            </div>
            <div id="tab-2">
                <form id="circleForm" action="ShapeCalculator" method="GET">
                    <input type="hidden" name="s" value="1"/>
                    Calculate the area of a circle by providing the:
                    <ul>
                        <li>Radius: <input name="radius" type="text" value="${radius}" placeholder="Radius" required /></li>
                    </ul>
                    <input type="submit" value="Calculate"/>
                </form>
            </div>
            <div id="tab-3">
                <form id="triangleForm" action="ShapeCalculator" method="GET">
                    <input type="hidden" name="s" value="2"/>
                    Calculate the length of a side of a triangle by providing the length of 2 sides:
                    <ul>
                        <li><img src="img/abctriangle.png"/></li>
                        <li>Side A: <input name="a" type="text" value="${a}" placeholder="A"/></li>
                        <li>Side B: <input name="b" type="text" value="${b}" placeholder="B"/></li>
                        <li>Side C: <input name="c" type="text" value="${c}" placeholder="C"/></li>
                    </ul>
                    <input type="submit" value="Calculate"/>
                </form>
            </div>

        </div>


        <script>
            //tabs
            $(function() {
                $('#tabs').tabs({heightStyle:'content'}).resizable().draggable();
                $('input[type=submit]').button();
            });

            //validation
            var requiredNumberRules = {required: true, range: [1, 999]};
            var nonRequiredNumberRules = {required: false, range: [1, 999]};
            var customSubmitHandler = function(form) {
                $.get(form.action, $(form).serialize(), function(data){
                    
                    var dialogContainer = $('#dialog-modal');
                    if($('#dialog-modal').length < 1){
                        $('<div/>')
                            .attr({'id':'dialog-modal', title:'Calculator Results'})
                            .appendTo(document.body);
                    }
                    $('#dialog-modal').html(data);//set the html
                    $('#dialog-modal').dialog({
                        height:200,
                        width:300,
                        modal:true
                    });
                });
            };

            jQuery.validator.setDefaults({
                debug: true,
                submitHandler: customSubmitHandler
            });

            $('#rectangleForm').validate({
                rules: {
                    length: requiredNumberRules,
                    width: requiredNumberRules
                }
            });
            $('#circleForm').validate({
                rules: {
                    radius: requiredNumberRules
                }
            });
            $('#triangleForm').validate({
                rules: {
                    a: nonRequiredNumberRules,
                    b: nonRequiredNumberRules,
                    c: nonRequiredNumberRules
                }
            });
        </script>

    </body>
</html>
