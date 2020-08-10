import React from 'react';
import { Container, Row, Col } from 'react-bootstrap'
import styles from '../static/styles.module.css'
import MyForm from './MyForm'
function Index(props) {
    return (
        <Container className={[styles.maxWidth, "mt-3"].join(" ")}>
            <Row className={styles.myMarginBottom}>
                <Col xs={9}>
                    <h3>Tạo sách mới</h3>
                </Col>
            </Row>
            <Row className="mt-5">
                <MyForm />
            </Row>
        </Container>
    );
}

export default Index;