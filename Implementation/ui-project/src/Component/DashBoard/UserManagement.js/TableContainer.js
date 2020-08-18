import React from 'react';
import { Table, Button } from 'react-bootstrap';
import styles from '../static/styles.module.css'
import { useHistory } from "react-router-dom";
import Moment from 'react-moment';
import config from '../../../asset/config.json'

function TableContainer(props) {
    const history = useHistory();

    const listItems = props.listReader.map((element, index) =>
        <tr key={index}>
            <td>{element.accountID}</td>
            <td>{element.fullName}</td>
            <td>{element.libraryCardID}</td>
            <td>
            <Button variant="secondary" className={styles.myButtonDetail} onClick={(event) => createReceiveBill(event, element)}><i className="fa fa-angle-double-right"></i></Button>
                <Button variant="primary" className={[styles.myButtonDetail, "ml-2"].join(" ")} onClick={() => detail(element)}><i className="fa fa-eye"></i></Button>
            </td>
        </tr>
    );

    function detail(data){
        props.byDetailEvent(data);
    }

    function createReceiveBill(event, data) {
        console.log("mydata", data);
        if (!data) {
            return;
        }
        history.push("/dashboard/new_renting_book_bill/" + data.accountID);
    }

    return (
        <Table striped bordered hover>
            <thead>
                <tr>
                    <th width={'10%'}>ID</th>
                    <th width={'50%'}>Tên</th>
                    <th width={'20%'}>Mã thẻ thư viện</th>
                    <th width={'15%'}>

                    </th>
                </tr>
            </thead>
            <tbody>
                {listItems}
            </tbody>
        </Table>
    );
}

export default TableContainer;