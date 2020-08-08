import React from 'react';
import { Row, Container, Col, Form, FormControl, Button } from 'react-bootstrap';
import MySelectedBox from './MySelectedBox'
import styles from '../static/styles.module.css'

function Body(props) {
    return (
        <Container>
            <Row className="mt-5 ml-5">
                <Form inline className={styles.maxWidth}>
                    <MySelectedBox />
                    <FormControl type="text" placeholder="Tìm kiếm" className={["mr-sm-2", styles.myQueryInput].join(" ")} />
                </Form>
            </Row>
            <Row className="mt-4 mb-4">
                <Col xs={5}>

                </Col>
                <Col xs={7} className="d-flex justify-content-end">
                    <Button variant="success" className={styles.myButtonSearch}><i className="fa fa-search"></i>&nbsp;Tìm kiếm</Button>
                </Col>
            </Row>
        </Container>
    );
}

export default Body;