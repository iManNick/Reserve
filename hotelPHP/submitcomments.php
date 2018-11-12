<?php	
	 $hotelname=$_GET["hotelname"];
	 $username=$_GET["username"];
	 $comment=$_GET["comment"];
	 $rating=$_GET["rating"];

  include_once 'config.php';
  
	
		$con=mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME);
		mysqli_set_charset($con,"utf8");
			
	if ($queryforhid = mysqli_query($con,"select `h_id` from hotel where `name` ='$hotelname'")){	  
	$hotelrow = $queryforhid->fetch_all(MYSQLI_ASSOC);

 	$h_id =  $hotelrow[0]['h_id'];

}


 	$qins=mysqli_query ($con,"INSERT INTO `comments`
	(`h_id`,`username`,`comment`,`rating`)VALUES
	('$h_id','$username','$comment','$rating');");
		if($qins)
		{
	$message = "نظر شما با موفقیت ثبت شد!";
		}
		else{
			$message = "Something went wrong!!";
		}
		

 echo json_encode(array(
        'message' => $message
        ));
 	
  //$qins="insert into reservation (r_id,r_chkindt,r_chkoutdt,r_rooms,r_type,r_name,r_email,r_company,r_phone,r_address,r_spanyreq) values('$id1','$cid','$cod','$norm','$type','$name','$email','$comp','$tele','$addr','$spreq')";
	//$tarcon=mysqli_connect("localhost","root","root",'tariff');
	
?>
