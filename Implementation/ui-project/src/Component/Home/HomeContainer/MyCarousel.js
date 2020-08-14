import React, { useEffect } from 'react';
import { Carousel } from 'react-bootstrap';
import styles from './static/styles.module.css'
import config from '../../../asset/config.json'
import { Link } from 'react-router-dom';

function MyCarousel(props) {
    const listItems = props.listData.map((element, index) =>
        <Carousel.Item key={index}>
            <Link to = '#'>
                <img
                    className={["d-block w-100", styles.myCarousel].join(" ")}
                    src={config.severAPi.hostUrl + ":8081" + element.path}
                    alt="First slide"
                />
            </Link>
            <Carousel.Caption>
                <h3>{element.releaseDate}</h3>
                <p>{element.author}</p>
            </Carousel.Caption>

        </Carousel.Item>
    );

    return (
        <Carousel>
            {listItems}
        </Carousel>
    );
}

export default MyCarousel;