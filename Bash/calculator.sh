#!/bin/bash

# Function to calculate factorial
factorial() {
  if (( $1 <= 1 )); then
    echo 1
  else
    echo $(( $1 * $(factorial $(( $1 - 1 ))) ))
  fi
}

# Function to perform calculations
calculate() {
  case $1 in
    "+")
      echo $(( $2 + $3 ))
      ;;
    "-")
      echo $(( $2 - $3 ))
      ;;
    "*")
      echo $(( $2 * $3 ))
      ;;
    "/")
      if (( $3 == 0 )); then
        echo "Error: Division by zero is not allowed."
        exit 1
      fi
      echo $(( $2 / $3 ))
      ;;
    "%")
      if (( $3 == 0 )); then
        echo "Error: Modulo by zero is not allowed."
        exit 1
      fi
      echo $(( $2 % $3 ))
      ;;
    "!")
      echo $(factorial $2)
      ;;
    *)
      echo "Error: Invalid calculation operand."
      exit 1
      ;;
  esac
}

# Main script
echo ">> Input calculation operand"
read operand

# Check if the operand is for factorial or not
if [[ $operand == "!" ]]; then
  echo ">> Input number for factorial"
  read num1
  result=$(calculate $operand $num1)
else
  echo ">> Input number1"
  read num1
  echo ">> Input number2"
  read num2
  result=$(calculate $operand $num1 $num2)
fi

echo ">> Result: $result"

