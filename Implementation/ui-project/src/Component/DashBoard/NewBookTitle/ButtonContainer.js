import React from 'react';
import styles from '../static/styles.module.css';
import { Row, Col, Button } from 'react-bootstrap';

function ButtonContainer(props) {
    function create(event){
        props.bindEventCreate();
    }
    return (
        <Row className="mt-3">
            <Col xs={7} className = "d-flex justify-content-end">
                <Button variant="success" className={styles.myButtonSearch} onClick={create}><i className="fa fa-plus"></i>&nbsp;Tạo</Button>
                <Button variant="danger" className={styles.myButtonSearch} ><i className="fa fa-times"></i>&nbsp;Hủy</Button>
            </Col>
        </Row>
    );
}

export default ButtonContainer;