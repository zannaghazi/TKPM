import React, { useEffect, useState } from 'react';
import { Container, Row, Col, Button } from 'react-bootstrap';
import styles from './static/styles.module.css';
import MyContainer from './MyContainer'

function Index(props) {
    return (
        <Container>
            <Row>
                <div className={styles.myTitle}><span className={styles.myText}>Chi tiết sách</span></div>
            </Row>
            <Row>
                <MyContainer />
            </Row>
        </Container>
    );
}

export default Index;