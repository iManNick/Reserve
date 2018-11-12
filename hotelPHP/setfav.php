<?php	

	 $hotelname=$_GET["hotelname"];
	$username = $_GET["username"];


	include_once 'config.php';
	$con=mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME);
	mysqli_set_charset($con,"utf8");
	
	
	if ($queryforhid = mysqli_query($con,"select `h_id` from hotel where `name` ='$hotelname'")){	  
	$hotelrow = $queryforhid->fetch_all(MYSQLI_ASSOC);

 	$h_id =  $hotelrow[0]['h_id'];

}


$result = mysqli_query($con,"INSERT INTO `userfav`
	(`h_id`,`username`)VALUES
	('$h_id','$username'); ");
	if ($result) {
	$message = "به لیست علاقه مندی های شما اضافه شد!";
}
else{
				$message = "در لیست علاقه مندی های شما موجود است!!";
}
 echo json_encode(array(
        'message' => $message
        ));


?>
