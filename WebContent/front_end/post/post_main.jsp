<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.posttype.model.*"%>
<%@ page import="java.util.*"%>


    
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Post Home</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i|Playfair+Display:400,400i,500,500i,600,600i,700,700i&subset=cyrillic" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="<%=request.getContextPath()%>/vendor/post/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/vendor/post/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/vendor/post/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/vendor/post/glightbox/css/glightbox.min.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/vendor/post/swiper/swiper-bundle.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
  

  <!-- Template Main CSS File -->
  <link href="<%=request.getContextPath()%>/front_end/css/post.css" rel="stylesheet" />

  <!-- =======================================================
  * Template Name: Lonely - v4.3.0
  * Template URL: https://bootstrapmade.com/free-html-bootstrap-template-lonely/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<style>
	p.category{
		color: gray !important;
		line-height: 30px !important;
		margin-top: 30px;
	}
</style>

<body>

  <!-- ======= Hero Section ======= -->
  <section id="hero" class="d-flex flex-column align-items-center justify-content-center">
    <h1></h1>
    <h2></h2>
    <a href="#about" class="btn-get-started scrollto"><i class="bi bi-chevron-double-down"></i></a>
  </section><!-- End Hero -->

  <!-- ======= Header ======= -->
  <header id="header" class="d-flex align-items-center">
    <div class="container d-flex align-items-center justify-content-between">

      <div class="logo">
        <h1><a href="<%=request.getContextPath()%>/front_end/index/index.jsp">Beloved</a></h1>
        <!-- Uncomment below if you prefer to use an image logo -->
        <!-- <a href="index.html"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->
      </div>

      <nav id="navbar" class="navbar">
        <ul>
          <li><a class="nav-link scrollto active" href="#hero">論壇首頁</a></li>
          <li><a class="nav-link scrollto" href="#about">介紹</a></li>                      
<!--           <li><a class="nav-link scrollto" href="#testimonials">最新文章</a></li> -->
          <li><a class="nav-link scrollto" href="#services">文章分類</a></li>                
        </ul>
        <i class="bi bi-list mobile-nav-toggle"></i>
      </nav><!-- .navbar -->

    </div>
  </header><!-- End Header -->

  <main id="main">

<!--     ======= About Section ======= -->
    <section id="about" class="about">
      <div class="container">

        <div class="row no-gutters">
          <div class="image col-xl-5 d-flex align-items-stretch justify-content-center justify-content-lg-start"></div>
          <div class="col-xl-7 ps-0 ps-lg-5 pe-lg-1 d-flex align-items-stretch">
            <div class="content d-flex flex-column justify-content-center">
              <h3>歡迎來到Beloved 論壇! </h3>
              <br>
              <br>
                	<h5>   Beloved 交友網站...</h5>
              </p>
              <div class="row">
                <div class="col-md-6 d-md-flex align-items-md-stretch">
                  <div class="count-box">
                    <i class="bi bi-emoji-smile"></i>
                    <span data-purecounter-start="0" data-purecounter-end="2000" data-purecounter-duration="1" class="purecounter"></span>
                    <p><strong>Happy Clients</strong> : 會員數突破 2000 人 </p>
                  </div>
                </div>

                <div class="col-md-6 d-md-flex align-items-md-stretch">
                  <div class="count-box">
                    <i class="bi bi-journal-richtext"></i>
                    <span data-purecounter-start="0" data-purecounter-end="4" data-purecounter-duration="1" class="purecounter"></span>
                    <p><strong>Topic</strong> : 文章有 4 大類別 </p>
                  </div>
                </div>

                <div class="col-md-6 d-md-flex align-items-md-stretch">
                  <div class="count-box">
                    <i class="bi bi-clock"></i>
                    <span data-purecounter-start="0" data-purecounter-end="1095" data-purecounter-duration="1" class="purecounter"></span>
                    <p><strong>Days of experience</strong> : 網站營運 3 年, 配對全年無休! </p>
                  </div>
                </div>

                <div class="col-md-6 d-md-flex align-items-md-stretch">
                  <div class="count-box">
                    <i class="bi bi-award"></i>
                    <span data-purecounter-start="0" data-purecounter-end="100" data-purecounter-duration="1" class="purecounter"></span>
                    <p><strong>Awards</strong> : 已成功媒合 100 對伴侶</p>
                  </div>
                </div>
              </div>
            </div><!-- End .content-->
          </div>
        </div>

      </div>
    </section>
<!-- End About Section -->

    
    
      <!-- ======= Services Section ======= -->
    <section id="services" class="services section-bg">
     <jsp:useBean id="postTypeSvc" scope="page" class="com.posttype.model.PostTypeService" />
      <div class="container">

        <div class="section-title">
          <h2>文章分類</h2>
          <p class="category"><h6>你有多久沒傾聽自己了呢 ? <br><br> 想找人聊天嗎 ? 想聽聽別人怎麼說嗎 ? <br><br>不管你是害羞還是外放, Beloved 論壇歡迎你來潛水跟分享 !  <i class="far fa-kiss-beam fa-lg"></i></h6></p>
        </div>

        <div class="row">
          <div class="col-md-6 col-lg-3 d-flex align-items-stretch mb-5 mb-lg-0">
            <div class="icon-box">
              <div class="icon"><a href="<%=request.getContextPath()%>/postType/PostTypeServlet?postTypeNo=1&action=listPostsBy_PostTypeNo_C"><i class="fas fa-suitcase-rolling"></i></a></div>
              <h4 class="title">旅遊</h4>
              <p class="description">太悶了... 二級疫情擋不住我的 !!  我就是要出去玩~ (口罩戴好戴滿)  </p>
            </div>
          </div>

          <div class="col-md-6 col-lg-3 d-flex align-items-stretch mb-5 mb-lg-0">
            <div class="icon-box">
              <div class="icon"><a href="<%=request.getContextPath()%>/postType/PostTypeServlet?postTypeNo=2&action=listPostsBy_PostTypeNo_C"><i class="fas fa-hamburger"></i></a></div>
              <h4 class="title">吃吃喝喝</h4>
              <p class="description"> 改天再減肥吧! 人活著就是要痛痛快快地吃喝呀! 肉肉也很美~  </p>
            </div>
          </div>

          <div class="col-md-6 col-lg-3 d-flex align-items-stretch mb-5 mb-lg-0">
            <div class="icon-box">
              <div class="icon"><a href="<%=request.getContextPath()%>/postType/PostTypeServlet?postTypeNo=3&action=listPostsBy_PostTypeNo_C"><i class="fab fa-gratipay"></i></a></div>
              <h4 class="title">兩性關係</h4>
              <p class="description">SOS~ 我好苦惱呀! 有人可以幫幫我嗎? 聽聽我訴苦也可以... </p>
            </div>
          </div>

          <div class="col-md-6 col-lg-3 d-flex align-items-stretch mb-5 mb-lg-0">
            <div class="icon-box">
              
              <div class="icon"><a href="<%=request.getContextPath()%>/postType/PostTypeServlet?postTypeNo=4&action=listPostsBy_PostTypeNo_C"><i class="far fa-laugh-wink"></i></a></div>
              <h4 class="title">其他</h4>
              <p class="description">左邊皆非就是這裡啦! 別害羞! 快來聊天吧~~ </p>
            </div>
          </div>

        </div>

      </div>
    </section><!-- End Services Section -->
  
   
  </main><!-- End #main -->
  

  <!-- ======= Footer ======= -->
  <footer id="footer">
    <div class="container">
      <div class="copyright">
        &copy; Copyright <strong><span>Beloved</span></strong> 2021
      </div>   
    </div>
  </footer><!-- End  Footer -->

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

 <!-- jQuery-->
 <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
 <!-- Bootstrap core JS-->
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>

  <!-- Vendor JS Files -->
  <script src="<%=request.getContextPath()%>/vendor/post/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="<%=request.getContextPath()%>/vendor/post/glightbox/js/glightbox.min.js"></script>
  <script src="<%=request.getContextPath()%>/vendor/post/isotope-layout/isotope.pkgd.min.js"></script>
  <script src="<%=request.getContextPath()%>/vendor/post/php-email-form/validate.js"></script>
  <script src="<%=request.getContextPath()%>/vendor/post/purecounter/purecounter.js"></script>
  <script src="<%=request.getContextPath()%>/vendor/post/swiper/swiper-bundle.min.js"></script>
  <script src="<%=request.getContextPath()%>/vendor/post/waypoints/noframework.waypoints.js"></script>

  <!-- Template Main JS File -->
  <script type="text/javascript">
  /**
  * Template Name: Lonely - v4.3.0
  * Template URL: https://bootstrapmade.com/free-html-bootstrap-template-lonely/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  */
  (function() {
    "use strict";

    /**
     * Easy selector helper function
     */
    const select = (el, all = false) => {
      el = el.trim()
      if (all) {
        return [...document.querySelectorAll(el)]
      } else {
        return document.querySelector(el)
      }
    }

    /**
     * Easy event listener function
     */
    const on = (type, el, listener, all = false) => {
      let selectEl = select(el, all)
      if (selectEl) {
        if (all) {
          selectEl.forEach(e => e.addEventListener(type, listener))
        } else {
          selectEl.addEventListener(type, listener)
        }
      }
    }

    /**
     * Easy on scroll event listener 
     */
    const onscroll = (el, listener) => {
      el.addEventListener('scroll', listener)
    }

    /**
     * Navbar links active state on scroll
     */
    let navbarlinks = select('#navbar .scrollto', true)
    const navbarlinksActive = () => {
      let position = window.scrollY + 200
      navbarlinks.forEach(navbarlink => {
        if (!navbarlink.hash) return
        let section = select(navbarlink.hash)
        if (!section) return
        if (position >= section.offsetTop && position <= (section.offsetTop + section.offsetHeight)) {
          navbarlink.classList.add('active')
        } else {
          navbarlink.classList.remove('active')
        }
      })
    }
    window.addEventListener('load', navbarlinksActive)
    onscroll(document, navbarlinksActive)

    /**
     * Scrolls to an element with header offset
     */
    const scrollto = (el) => {
      let header = select('#header')
      let offset = header.offsetHeight

      if (!header.classList.contains('header-scrolled')) {
        offset -= 16
      }

      let elementPos = select(el).offsetTop
      window.scrollTo({
        top: elementPos - offset,
        behavior: 'smooth'
      })
    }

    /**
     * Header fixed top on scroll
     */
    let selectHeader = select('#header')
    if (selectHeader) {
      let headerOffset = selectHeader.offsetTop
      let nextElement = selectHeader.nextElementSibling
      const headerFixed = () => {
        if ((headerOffset - window.scrollY) <= 0) {
          selectHeader.classList.add('fixed-top')
          nextElement.classList.add('scrolled-offset')
        } else {
          selectHeader.classList.remove('fixed-top')
          nextElement.classList.remove('scrolled-offset')
        }
      }
      window.addEventListener('load', headerFixed)
      onscroll(document, headerFixed)
    }

    /**
     * Back to top button
     */
    let backtotop = select('.back-to-top')
    if (backtotop) {
      const toggleBacktotop = () => {
        if (window.scrollY > 100) {
          backtotop.classList.add('active')
        } else {
          backtotop.classList.remove('active')
        }
      }
      window.addEventListener('load', toggleBacktotop)
      onscroll(document, toggleBacktotop)
    }

    /**
     * Mobile nav toggle
     */
    on('click', '.mobile-nav-toggle', function(e) {
      select('#navbar').classList.toggle('navbar-mobile')
      this.classList.toggle('bi-list')
      this.classList.toggle('bi-x')
    })

    /**
     * Mobile nav dropdowns activate
     */
    on('click', '.navbar .dropdown > a', function(e) {
      if (select('#navbar').classList.contains('navbar-mobile')) {
        e.preventDefault()
        this.nextElementSibling.classList.toggle('dropdown-active')
      }
    }, true)

    /**
     * Scrool with ofset on links with a class name .scrollto
     */
    on('click', '.scrollto', function(e) {
      if (select(this.hash)) {
        e.preventDefault()

        let navbar = select('#navbar')
        if (navbar.classList.contains('navbar-mobile')) {
          navbar.classList.remove('navbar-mobile')
          let navbarToggle = select('.mobile-nav-toggle')
          navbarToggle.classList.toggle('bi-list')
          navbarToggle.classList.toggle('bi-x')
        }
        scrollto(this.hash)
      }
    }, true)

    /**
     * Scroll with ofset on page load with hash links in the url
     */
    window.addEventListener('load', () => {
      if (window.location.hash) {
        if (select(window.location.hash)) {
          scrollto(window.location.hash)
        }
      }
    });

    /**
     * Porfolio isotope and filter
     */
    window.addEventListener('load', () => {
      let portfolioContainer = select('.portfolio-container');
      if (portfolioContainer) {
        let portfolioIsotope = new Isotope(portfolioContainer, {
          itemSelector: '.portfolio-item',
          layoutMode: 'fitRows'
        });

        let portfolioFilters = select('#portfolio-flters li', true);

        on('click', '#portfolio-flters li', function(e) {
          e.preventDefault();
          portfolioFilters.forEach(function(el) {
            el.classList.remove('filter-active');
          });
          this.classList.add('filter-active');

          portfolioIsotope.arrange({
            filter: this.getAttribute('data-filter')
          });
        }, true);
      }

    });

    /**
     * Initiate portfolio lightbox 
     */
    const portfolioLightbox = GLightbox({
      selector: '.portfolio-lightbox'
    });

    /**
     * Portfolio details slider
     */
    new Swiper('.portfolio-details-slider', {
      speed: 400,
      loop: true,
      autoplay: {
        delay: 5000,
        disableOnInteraction: false
      },
      pagination: {
        el: '.swiper-pagination',
        type: 'bullets',
        clickable: true
      }
    });

    /**
     * Skills animation
     */
    let skilsContent = select('.skills-content');
    if (skilsContent) {
      new Waypoint({
        element: skilsContent,
        offset: '80%',
        handler: function(direction) {
          let progress = select('.progress .progress-bar', true);
          progress.forEach((el) => {
            el.style.width = el.getAttribute('aria-valuenow') + '%'
          });
        }
      })
    }

    /**
     * Testimonials slider
     */
    new Swiper('.testimonials-slider', {
      speed: 600,
      loop: true,
      autoplay: {
        delay: 5000,
        disableOnInteraction: false
      },
      slidesPerView: 'auto',
      pagination: {
        el: '.swiper-pagination',
        type: 'bullets',
        clickable: true
      },
      breakpoints: {
        320: {
          slidesPerView: 1,
          spaceBetween: 40
        },

        1200: {
          slidesPerView: 3,
        }
      }
    });

  })()
 
  </script>
  
  	
  

</body>

</html>