import React, { useEffect } from 'react';
import { Container, Row, Col, Button } from 'react-bootstrap';
import styles from './static/styles.module.css';
import Image4 from '../../../asset/picture/srcTest/sach4.jpg';
import Info from './Info'

function Index(props) {
    useEffect(() => {
        console.log("1234", props.match.params.id);
    });
    return (
        <Container>
            <Row>
                <div className={styles.myTitle}><span className={styles.myText}>Chi tiết sách</span></div>
            </Row>
            <Row>
                <Col sm={3}>
                    <img src={Image4} alt="img" className={["d-block w-100",styles.myCarousel].join(" ")} />
                </Col>
                <Col sm={9}>
                    <Info />
                </Col>
            </Row>
        </Container>
    );
}

export default Index;