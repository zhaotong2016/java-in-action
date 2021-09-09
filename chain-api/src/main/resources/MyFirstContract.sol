pragma solidity >=0.5.0 <0.7.0;

contract MyFirstContract {

    uint storedData;

    address payable owner;

    constructor() public {
        owner = msg.sender;
    }


    function set(uint x) public {
        storedData = x;
    }

    function get() public view returns (uint) {
        return storedData;
    }

    function kill() public{
        if (owner == msg.sender) {
            selfdestruct(owner);
        }
    }
}