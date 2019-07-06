<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Write a JavaScript function to repeat a string a specified times.</title>
</head>
<body>
<script>
var today=new Date();
var day=today.getDay();
var hour=today.getHours();
var minute=today.getMinutes();
var second=today.getSeconds();
var prepand;
var daylist=["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday", "saturday"]
document.write("Today is "+ daylist[day]+'<br>');
if(hour>=12)
{
  prepand='PM';
hour=hour-12;
}
else
{
  prepand='AM'
hour=hour;
}
console.log(prepand)
if(hour===0 && prepand==='PM')
{
if(minute===0 && second===0)
{
  prepand='Noon';
  hour=12;
}
  else
  {
    hour=12;
  prepand='PM'
  }
}
if(hour===0 && prepand==='AM')
   {
   if(minute===0 && second===0)
{ prepand=='Midnight';
hour=12;
}
else{
prepand='AM'
  hour=12;
}
   }
   document.write("the Time now is "+hour+":"+minute+":"+second+" "+prepand)
   </script>
   </body>
</html>
