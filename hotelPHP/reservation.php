<?php	
	session_start();	 
	 $cid=$_GET["cid"];
	 $cod=$_GET["cod"];
	 $norm=$_GET["txtroom"];
	 $type=$_GET["txttype"];
	 $hotelname=$_GET["txtspanyreq"];
	 $username = $_GET["username"];	
	$ddiff=floor(strtotime($cod)-strtotime($cid))/86400;
	$_SESSION['datediff']=$ddiff;

  include_once 'config.php';

	if($ddiff<0)
	{
		$message = "CheckIn Date cant be greater than Checkout Date :()";
		
		 echo json_encode(array(
        		'message' => $message
        ));
	}
	else
	{
		$con=mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME);
		mysqli_set_charset($con,"utf8");
		      
	if ($queryforhid = mysqli_query($con,"select `h_id` from hotel where `name` ='$hotelname'")){	  
	$hotelrow = $queryforhid->fetch_all(MYSQLI_ASSOC);

 	$spreq =  $hotelrow[0]['h_id'];

}

 			   
				
		
	$qq=mysqli_query($con,"select * from reservation");
	$row=mysqli_fetch_array($qq);
	$id1=mysqli_num_rows($qq)+1;
 $b_rooms=0;
 $query = "select sum(`r_rooms`) as booked_rooms from `reservation` where (`r_chkoutdt`>'$cid' and `r_chkindt`<'$cod'  and `r_type`='$type' and `hotel` = '$spreq')";
 $new = mysqli_query($con,$query);
 while ($row = mysqli_fetch_assoc($new)) {
  		$b_rooms= $row['booked_rooms'];
 }
	$totalrooms_query="SELECT totroom FROM `tariff` where `type`='$type' and `h_id` = '$spreq'";
	$totalrooms_query_q=mysqli_query($con,$totalrooms_query);
	$t_rooms=0;
	while ($row = mysqli_fetch_assoc($totalrooms_query_q)) {
  		$t_rooms= $row['totroom'];
 }
 	$a_rooms=$t_rooms-$b_rooms;
 	if($norm>$a_rooms)
 	{
 		$message = " تنها به تعداد ".$a_rooms." اتاق از نوع ".$type." این هتل در تاریخ انتخاب شده خالی می باشد ";
		
 echo json_encode(array(
        'message' => $message
        ));
 	}
 	else
 	{
 	$qins=mysqli_query ($con,"INSERT INTO `reservation`
	(`r_id`,`r_chkindt`,`r_chkoutdt`,`r_rooms`,`r_type`,`hotel`)VALUES
	('$id1','$cid','$cod','$norm','$type','$spreq');");
		if($qins)
		{
				 $app="update tariff set avroom=avroom-$norm where type='$type' and h_id =$spreq ";
				 mysqli_query($con,$app);
		}
		mysqli_query ($con,"INSERT INTO `maprt`
		(`r_id`,`type`)VALUES
		('$id1','$type');");
		$_SESSION['rid']=$id1;
		mysqli_query ($con,"INSERT INTO `mapur`
		(`Userid`,`r_id`)VALUES
		('$username','$id1');");
		$qq=mysqli_query($con,"select max(room_number) as 'maxrn' from room");
		while($res = mysqli_fetch_assoc($qq)){
			$roomno = $res['maxrn'];
		}
		for ($x = 1; $x <= $norm; $x++) {
			$roomno = $roomno +1;
			mysqli_query ($con,"INSERT INTO `maprr`
			(`r_id`,`room_number`)VALUES
			('$id1','$roomno');");
			mysqli_query ($con,"INSERT INTO `room`
			(`room_number`,`r_id`,`type`,`CheckIn Date`,`CheckOut Date`)VALUES
			('$roomno','$id1','$type','$cid','$cod');");
		}
	$message = "رزرو موفق!";
	$_SESSION['norm']=$norm;
	$_SESSION['type']=$type;

 echo json_encode(array(
        'message' => $message
        ));
 	}
  //$qins="insert into reservation (r_id,r_chkindt,r_chkoutdt,r_rooms,r_type,r_name,r_email,r_company,r_phone,r_address,r_spanyreq) values('$id1','$cid','$cod','$norm','$type','$name','$email','$comp','$tele','$addr','$spreq')";
	//$tarcon=mysqli_connect("localhost","root","root",'tariff');
	}
?>
