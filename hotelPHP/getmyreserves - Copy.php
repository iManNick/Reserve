<?php	
	$username = $_GET["username"];
	include_once 'config.php';
	$con=mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME);
	mysqli_set_charset($con,"utf8");
	$reserves = mysqli_query($con,"select * from `reservation` r inner join hotel h ON h.h_id = r.hotel where `r_id` in (select `r_id` from `mapur` where `Userid` = '$username')  ");
	//$r_ids = mysqli_query($con,"select * from `mapur` where `Userid` = '$username'");
	//$StackArray = array();
	//$roomnumsstring = '';
	
	//while ($rowID = mysqli_fetch_assoc($r_ids))
 	//{
	 //	$tmp = $rowID['r_id'];
	//	$reserves = mysqli_query($con,"select * from `reservation` where `r_id` = '$tmp' ");
		//$reservesArray = $reserves->fetch_all(MYSQLI_ASSOC);
	//	$roomnumbers = mysqli_query($con,"select * from `maprr` where `r_id` = '$tmp' ");
	//	  while ($roomnum = mysqli_fetch_assoc($roomnumbers)){
	//		   $roomnumsstring .= $roomnum['room_number']. '-';
//		  }  
 
//		 $StackArray = array_merge($reservesArray, array("room_number" => $roomnumsstring) );	
 		 //$StackArray($reservesArray, $reservesArray);
		 
 	//}
	
		$reservesArray = $reserves->fetch_all(MYSQLI_ASSOC);
	echo json_encode($reservesArray);

		$resultArray = array('myreserveList'=>$reservesArray);
			//echo json_encode($resultArray);
		//echo json_encode($resultArray);
	
//Copy result into a associative array
//$Array = $reserves->fetch_all(MYSQLI_ASSOC);
//$resultArray = array('myreserveList'=>$Array);
//echo json_encode($resultArray);


?>