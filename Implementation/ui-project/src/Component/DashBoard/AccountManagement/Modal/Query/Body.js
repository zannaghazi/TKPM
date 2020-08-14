import React, {useState} from 'react';
import { Row, Container, Col, Form, FormControl, Button } from 'react-bootstrap';
import { connect } from 'react-redux';
import * as actions from '../../../../../actions/index';
import MySelectedBox from './MySelectedBox'
import styles from '../static/styles.module.css'
import { useHistory } from "react-router-dom";
import config from '../../../../../asset/config.json'

function Body(props) {
    const history = useHistory();
    const [type, setType] = useState(1);
    const [query, setQuery] = useState("");
    function NewAccount(event){
        history.push("/dashboard/new_account");
    }

    function search(event){
    //     let typeSeacch = "Name";
    //     switch(type){
    //         case 0: typeSeacch = "Name";
    //         break;
    //         case 1: typeSearch = " "
    //     }
    //     let body ={
    //         type: ""
    //     }
    //     fetch(config.severAPi.hostUrl +":8081/book/search", {
    //     method: "post",
    //     headers: { "Content-Type": "application/json" },
    //     body: body
    //   })
    //     .then(res => res.json())
    //     .then(data => {
    //       if (data.message === "Success") {
    //         this.setAuth(data);
    //          localStorage.internetbanking_refreshtoken = data.refresh_token;
    //         const retUrl = this.$route.query.retUrl || '/dashboard';
    //         return this.$router.push(retUrl);
    //       } else {
    //         this.err = data.error;
    //         return;
    //       }
    //     });
    }

    function byValue(data){
        console.log(data);
        setType(data);
    }

    function handleChange(event){
        setQuery(event.target.value);
    }

    return (
        <Container>
            <Row className="mt-5 ml-5">
                <Form inline className={styles.maxWidth}>
                    <MySelectedBox byValue={byValue}/>
                    <FormControl type="text" placeholder="Tìm kiếm" className={["mr-sm-2", styles.myQueryInput].join(" ")} onChange={handleChange}/>
                </Form>
            </Row>
            <Row className="mt-4 mb-4">
                <Col xs={5}>

                </Col>
                <Col xs={7} className="d-flex justify-content-end">
                    <Button variant="primary" className={styles.myButtonSearch}  onClick={NewAccount}><i className="fa fa-plus"></i>&nbsp;Tạo tài khoản</Button>
                    <Button variant="success" className={styles.myButtonSearch} onClick = {search}><i className="fa fa-search"></i>&nbsp;Tìm kiếm</Button>
                </Col>
            </Row>
        </Container>
    );
}

const mapStateToProps = state => {
	return {

	}
}

const mapDispatchToProps = (dispatch, props) => {
	return {
		onSetListAccount: (listAccount) => {
			dispatch(actions.setListAccount(listAccount));
		}
	}
}

export default connect(mapStateToProps, mapDispatchToProps)(Body);