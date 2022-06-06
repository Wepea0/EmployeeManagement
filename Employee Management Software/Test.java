public class Test{
    public static void drawRuler(int nInches, int majorLength) { 
        drawLine(majorLength, 0); // draw inch 0 line and label
         for (int j = 1; j <= nInches; j++) { 
            drawInterval(majorLength  -1); // draw interior ticks for inch
        drawLine(majorLength, j); // draw inch j line and label
         } 
    } 
        private static void drawInterval(int centralLength) { 
            if (centralLength >= 1) { // otherwise, do nothing
        drawInterval(centralLength -1); // recursively draw top interval
        drawLine(centralLength); // draw center tick line (without label)
        drawInterval(centralLength -1); // recursively draw bottom interval
         }
     } 
    private static void drawLine(int tickLength, int tickLabel) { 
        for (int j = 0; j < tickLength; j++)
            System.out.print("-");
        if (tickLabel >= 0)
            System.out.print(" " + tickLabel);
            System.out.print("\n");
        } 
        /** Draws a line with the given tick length (but no label). */
        private static void drawLine(int tickLength) { 
            drawLine(tickLength, -1);
        }

        public static int recurseMultiply(int firstNum, int secondNum){
            if(secondNum > firstNum){
                recurseMultiply(secondNum, firstNum);
            }
            if(secondNum == 0){
                return 0;
            }
            return firstNum + recurseMultiply(firstNum, secondNum -1);
        }

        public static int binarySearch(int [] testArr, int req, int high, int low){
            int mid = low + (high-low)/2;
            System.out.println("Mid - " + mid);
            int currNum = testArr[mid];
            System.out.println("Current number - " + currNum);
            if(currNum == req){
                System.out.println("Correct");
                return mid;
            }
            else if(currNum > req){
                high = mid;
                if(high == low){
                    System.out.println("Return 1");
                    return -1;
                }
                else{
                    return binarySearch(testArr, req, high, low);
                    //System.out.println("High - " + high);
                }
            }   
            else if(currNum < req){
                low = mid + 1;
                if(high == low){
                    System.out.println("Return 2");

                    return -1;
                }
                else{
                    return binarySearch(testArr, req, high, low);
                }

            } 
            return -1;
                                                      
            
        }
    public static void main(String[] args){
        //System.out.println(recurseMultiply(3, 8));
        int[] testArr = {2, 4, 6, 8, 10, 11, 13, 14, 15, 16, 17};
        System.out.println(binarySearch(testArr, 4, 11, 0));

        
    }


    }
