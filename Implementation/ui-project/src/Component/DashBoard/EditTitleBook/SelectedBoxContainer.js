import React from 'react';
import styles from '../static/styles.module.css';
import Select from 'react-select'
import {Row, Col} from 'react-bootstrap'

const options = [
    { value: 'chocolate', label: 'Chocolate' },
    { value: 'strawberry', label: 'Strawberry' },
    { value: 'vanilla', label: 'Vanilla' }
  ]

function SeletedBoxContainer(props) {
    return (
        <Row className = "mt-1">
            <Col xs={2} className={["d-flex justify-content-end align-items-center", styles.myLabel].join(" ")}>
                {props.label}
            </Col>
            <Col xs={6} className = "pl-3">
                <Select className={styles.myAuthorSelect} options={options} />
            </Col>
        </Row>
    );
}

export default SeletedBoxContainer;