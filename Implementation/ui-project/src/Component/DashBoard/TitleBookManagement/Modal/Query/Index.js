import React, {useState} from 'react';
import {Modal} from 'react-bootstrap';
import Body from './Body';

function UserModal(props) {
    
    return (
        <Modal
            size="lg"
            show={props.show}
            onHide={() => props.bindEventShow(false)}
            aria-labelledby="example-modal-sizes-title-lg"
        >
            <Modal.Header closeButton>
                <Modal.Title id="example-modal-sizes-title-lg">
                    Tìm kiếm đầu sách
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Body/>
            </Modal.Body>
        </Modal>
    );
}

export default UserModal;