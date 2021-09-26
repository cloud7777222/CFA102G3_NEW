<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>topbar</title>

</head>

<body>

	 <div class="top-bar d-none d-md-block">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-8">
                    <div class="top-bar-left">
                        <div class="text">
                            <i class="fas fa-bullhorn"></i>
                            <p>最新消息</p>
                        </div>
                        <div class="text">
                            <i class="far fa-comment-dots"></i>
                            <p id="news">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Aliquam, rerum!
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="top-bar-right">
                        <div class="social">
                            <a href=""><i class="fab fa-github"></i></a>
                            <a href=""><i class="far fa-envelope"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Top Bar End -->

    <!-- Nav Bar Start -->
    <div class="navbar navbar-expand-lg bg-dark navbar-dark">
        <div class="container-fluid">
            <a href="index.html" class="navbar-brand">BELOVED</a>
            <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                <div class="navbar-nav ml-auto">
                    <a href="index.html" class="nav-item nav-link active">Home</a>
                    <a href="about.html" class="nav-item nav-link">About</a>
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Services</a>
                        <div class="dropdown-menu">
                            <a href="single.html" class="dropdown-item">Dating</a>
                            <a href="service.html" class="dropdown-item">Consulting</a>
                            <a href="team.html" class="dropdown-item">Activity</a>
                        </div>
                    </div>
                    <a href="causes.html" class="nav-item nav-link">Article</a>
                    <a href="event.html" class="nav-item nav-link">Shop</a>
                    <a href="contact.html" class="nav-item nav-link">Contact</a>

                    <div>
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown"><i
                                    class="fas fa-user fa-fw"></i></a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#">Settings</a></li>
                                <li><a class="dropdown-item" href="#">Activity Log</a></li>
                                <li>
                                    <hr class="dropdown-divider" />
                                </li>
                                <li><a class="dropdown-item">Logout</a></li>
                            </ul>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

</body>
</html>