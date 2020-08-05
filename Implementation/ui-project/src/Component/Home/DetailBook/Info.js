import React from 'react';
import {Card, Container, Row} from 'react-bootstrap';
import styles from './static/styles.module.css'

function Info(props) {
    return (
        <Card>
            <Card.Body>
                <Container>
                    <Row className={[styles.myMarginBottom, "pb-2"].join(" ")}>
                        <h3><b>Mẹ luôn đồng hành cùng con</b></h3>
                    </Row>
                    <Row>
                        
                    </Row>
                </Container>
            </Card.Body>
        </Card>
    );
}

export default Info;