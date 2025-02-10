<?php
$message = 'Hello, this is a test email.';
$success = mail('jukes.orsatti@gmail.com', 'My Subject', $message);
if (!$success) {
    echo $errorMessage = error_get_last()['message'];
}