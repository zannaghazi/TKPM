import * as types from '../constants/ActionType';
var initialState = [];

var myReducer = (state = initialState, action) => {
    switch (action.type) {
        case types.SET_LIST_ROLE:
            state = action.listRole;
            return [...state];
        default:
            return state;
    }
    return state;
}

export default myReducer;