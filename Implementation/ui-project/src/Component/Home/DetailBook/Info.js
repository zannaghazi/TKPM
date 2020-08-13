import React from 'react';
import {Card, Container, Row} from 'react-bootstrap';
import styles from './static/styles.module.css'

function Info(props) {
    return (
        <Card className={styles.myCardContainer}>
            <Card.Body>
                <Container>
                    <Row className={[styles.myMarginBottom, "pb-5", "mt-5"].join(" ")}>
                        <h3><b>Mẹ luôn đồng hành cùng con</b></h3>
                    </Row>
                    <Row className="mt-5">
                        <h5><b>ISBN:&nbsp;</b>data</h5>
                    </Row>
                    <Row className="mt-3">
                        <h5><b>Vị trí:&nbsp;</b>data</h5>
                    </Row>
                    <Row className="mt-3">
                        <h5><b>Thể loại:&nbsp;</b>data</h5>
                    </Row>
                    <Row className="mt-3"> 
                        <h5><b>Nhà xuất bản:&nbsp;</b>data</h5>
                    </Row>
                    <Row className="mt-3">
                        <h5><b>Ngày phát hành:&nbsp;</b>data</h5>
                    </Row>
                </Container>
            </Card.Body>
        </Card>
    );
}

export default Info;