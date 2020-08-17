import React from 'react';
import styles from '../static/styles.module.css';
import { Row, Col, Button } from 'react-bootstrap';
import { useHistory } from "react-router-dom";

function ButtonContainer(props) {
    const history = useHistory();

    return (
        <Row className="mt-3">
            <Col xs={9} className = "d-flex justify-content-end">
                <Button variant="success" className={styles.myButtonSearch} onClick={props.byEventUpdate} ><i className="fa fa-check"></i>&nbsp;Cập nhật</Button>
                <Button variant="danger" className={styles.myButtonSearch}  onClick={props.byEventCancel}><i className="fa fa-times"></i>&nbsp;Hủy</Button>
            </Col>
        </Row>
    );
}

export default ButtonContainer;