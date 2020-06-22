import React from 'react';
import {Carousel} from 'react-bootstrap';
import Carousel1 from '../../asset/picture/srcTest/sach1.jpg'
import Carousel2 from '../../asset/picture/srcTest/sach2.jpg'
import Carousel3 from '../../asset/picture/srcTest/sach3.jpg'
import styles from './static/styles.module.css'

function MyCarousel(props) {
    return (
        <Carousel>
            <Carousel.Item>
                <img
                    className={["d-block w-100",styles.myCarousel].join(" ")}
                    src={Carousel1}
                    alt="First slide"
                />
                <Carousel.Caption>
                    <h3>22/06/2020</h3>
                    <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
                </Carousel.Caption>
            </Carousel.Item>
            <Carousel.Item>
                <img
                    className={["d-block w-100",styles.myCarousel].join(" ")}
                    src={Carousel2}
                    alt="Third slide"
                />

                <Carousel.Caption>
                    <h3>22/06/2020</h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                </Carousel.Caption>
            </Carousel.Item>
            <Carousel.Item>
                <img
                    className={["d-block w-100",styles.myCarousel].join(" ")}
                    src={Carousel3}
                    alt="Third slide"
                />

                <Carousel.Caption>
                    <h3>22/06/2020</h3>
                    <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur.</p>
                </Carousel.Caption>
            </Carousel.Item>
        </Carousel>
    );
}

export default MyCarousel;