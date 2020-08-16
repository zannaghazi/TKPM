import React from 'react';
import {Table, Button} from 'react-bootstrap';
import styles from '../static/styles.module.css'
import { useHistory } from "react-router-dom";

function TableContainer(props) {
    const history = useHistory();
    
    function editItem(event, data) {
        console.log("mydata", data);
        if (!data) {
            return;
        }
        history.push("/dashboard/edit_publisher/"+data.id);
    }

    const listItems = props.listPublisher.map((element, index) =>
        <tr key={index}>
            <td>{element.id}</td>
            <td>{element.name}</td>
            <td>
                <Button variant="primary" className={styles.myButtonDetail} onClick={(event) => editItem(event, element)}><i className="fa fa-edit"></i></Button>
                <Button variant="danger" className={[styles.myButtonDetail, "ml-2"].join(" ")}><i className="fa fa-trash"></i></Button>
            </td>
        </tr>
    );

    return (
        <Table striped bordered hover>
            <thead>
                <tr>
                    <th width={'10%'}>#</th>
                    <th width={'70%'}>Tên</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                {listItems}
            </tbody>
        </Table>
    );
}

export default TableContainer;