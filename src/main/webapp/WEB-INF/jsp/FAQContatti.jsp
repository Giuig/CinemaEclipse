<%@include file="/WEB-INF/jspf/header.jspf"%>
    
    <title>FAQ e Contattaci - Cinema Eclipse</title>

<%@include file="/WEB-INF/jspf/navbar.jspf"%>

<div class="container py-5 exo">
    <img class="w-100 d-block" src="../res/faq.jpg" alt="">
    <div class="container">
    
     <div>
        <img src="res/luna.png" width="35px">
         <span id="intestazione">FAQ</span>
      </div>
   
        <div class="font text-light px-3 py-3">
            <div id="accordion">
                <div>
                    <div id="headingOne">
                        <h5 class="mb-0">
                            <button id="faq" class="btn btn-link w-100 my-2" data-toggle="collapse" data-target="#collapseOne"
                                aria-expanded="true" aria-controls="collapseOne">
                                ESISTE DAVVERO QUESTO CINEMA?
                            </button>
                        </h5>
                    </div>
                    <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">
                        <div class="text-justify w-100">
                            Lorem ipsum, dolor sit amet consectetur adipisicing elit. Repellendus nobis nam blanditiis
                            voluptates sit assumenda provident et, facere inventore illo, expedita quidem minima officia
                            hic! Perspiciatis unde aliquam tempore molestias!
                            Ullam quis, perspiciatis natus deleniti consectetur aliquam expedita rerum optio porro culpa
                            voluptatibus earum nobis ea odit dignissimos sapiente dolores reiciendis, odio, unde quae
                            facere eius possimus obcaecati temporibus. Magnam?
                        </div>
                    </div>
                </div>
                <div>
                    <div id="headingTwo">
                        <h5 class="mb-0">
                            <button id="faq" class="btn btn-link collapsed w-100 my-2" data-toggle="collapse"
                                data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                HO DIMENTICATO LA PASSWORD. COME LA RECUPERO?
                            </button>
                        </h5>
                    </div>
                    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                        <div class="text-justify w-100">
                            Lorem ipsum dolor sit amet consectetur, adipisicing elit. Eveniet mollitia numquam
                            dignissimos deserunt ut amet fugiat asperiores? Sequi nihil distinctio rem sed a laborum
                            doloremque. Tempore et consequatur expedita odit!
                            Voluptatem numquam iste animi labore repellendus laborum, inventore aliquid accusantium
                            assumenda ab optio quae nulla sint explicabo qui cupiditate rerum odio voluptates nesciunt
                            illum nihil consectetur ut. Ipsam, magni perspiciatis?
                            Minima nihil officiis animi fugit, atque quidem labore aspernatur distinctio optio modi
                            velit nobis veniam. Ipsa minima quia architecto aut possimus quisquam totam accusantium,
                            facere aliquam? Unde nam tempora eveniet.
                        </div>
                    </div>
                </div>
                <div>
                    <div id="headingThree">
                        <h5 class="mb-0">
                            <button id="faq" class="btn btn-link collapsed w-100 my-2" data-toggle="collapse"
                                data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                QUANDO ESCE IL FILM DI JSON?
                            </button>
                        </h5>
                    </div>
                    <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">
                        <div class="text-justify w-100">
                            Lorem ipsum dolor sit amet consectetur adipisicing elit. Alias consequatur magnam ex rem
                            placeat, esse velit magni quo officia aliquid mollitia assumenda id optio accusamus!
                            Repellendus possimus perferendis totam delectus!
                        </div>
                    </div>
                </div>
                <div>
                    <div id="headingFour">
                        <h5 class="mb-0">
                       		<button id="faq" class="btn btn-link collapsed w-100 my-2" data-toggle="collapse"
                                data-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                QUANDO APRIRA' IL CINEMA ECLIPSE A CANECATTI'?
                            </button>
                        </h5>
                    </div>
                    <div id="collapseFour" class="collapse" aria-labelledby="headingFour" data-parent="#accordion">
                        <div class="text-justify w-100">
                            Lorem ipsum dolor sit amet consectetur adipisicing elit. Eum atque amet, sapiente architecto
                            quasi id obcaecati, doloremque nostrum cum praesentium animi, tempore neque eaque voluptates
                            itaque ab! Culpa, esse molestias?
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <div>
        <img src="res/luna.png" width="35px">
         <span id="intestazione">CONTATTACI</span>
      </div>
        <div class="px-4 font py-3">
            
            <h6 class="text-white exo">Per domande, dubbi o perplessita' non esitare a contattarci a 
            <a class = "orange" href="mailto:cnmaeclipse@gmail.com">cnmaeclipse@gmail.com</a>
            </h6>
            
            
        </div>
    </div>
</div>


<%@include file="/WEB-INF/jspf/footer.jspf"%>