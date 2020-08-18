import React from 'react';
import styles from '../static/styles.module.css';
import { Row, Col, Button } from 'react-bootstrap';
import { useHistory } from "react-router-dom";
import { useParams } from 'react-router-dom';

function ButtonContainer(props) {
    const history = useHistory();
    const params = useParams();
    function Cancel(event){
        history.push("/dashboard/user_management");
    }

    return (
        <Row className="mt-3">
            <Col xs={8} className = "d-flex justify-content-end">
                <Button variant="success" className={styles.myButtonSearch} onClick={props.byEventCreate}><i className="fa fa-plus"></i>&nbsp;Tạo</Button>
                <Button variant="danger" className={styles.myButtonSearch} onClick={Cancel} ><i className="fa fa-times"></i>&nbsp;Hủy</Button>
            </Col>
        </Row>
    );
}

export default ButtonContainer;