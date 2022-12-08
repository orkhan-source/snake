package com.codenjoy.dojo.snake.client;

import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.snake.model.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    public static boolean noWay(Board b){
        Point head = b.getHead();
        Point stone = b.getStones().get(0);
        stone.distance(head);
        List<Point> barriers = b.getBarriers();

        if(b.getSnake().size() > 15)
            if(barriers.stream().anyMatch(ba -> (ba.getX() == head.getX() && ba.getY() == head.getY() + 1))){
                if(barriers.stream().anyMatch(ba -> ba.getX() == head.getX() && ba.getY() == head.getY() - 1)){
                    if(barriers.stream().anyMatch(ba -> ba.getX() == head.getX() + 1 && ba.getY() == head.getY()))
                        return barriers.stream().anyMatch(ba -> ba.getX() == head.getX() - 1 && ba.getY() == head.getY());
                }
            }
        return false;
    }

    public static boolean isClosed(List<Point> snake){


        return false;
    }

    public static Direction correctDir(Board b) {
        List<Point> snake = b.getSnake();
        Direction snakeDir = b.getSnakeDirection();
        Point head = b.getHead();
        List<Point> walls = b.getWalls();
        Direction correctDir = null;


        if (snakeDir == Direction.UP || snakeDir == Direction.DOWN) {
            if (walls.stream().anyMatch(w -> (w.getX() == head.getX() && w.getY() == head.getY() + 1)
                    || (w.getX() == head.getX() && w.getY() == head.getY() - 1))) {
                if(snakeDir == Direction.UP){
                    if (snake.stream().anyMatch(s -> (s.getX() < head.getX() && s.getY() == head.getY())
                            ||(s.getX() == 1  && s.getY() > 7)))  return Direction.RIGHT;
                    else if (snake.stream().anyMatch(s -> (s.getX() > head.getX() && s.getY() == head.getY())
                            ||( s.getX() == 13 && s.getY() > 7))) return Direction.LEFT;
                }
                else {
                    if (snake.stream().anyMatch(s -> (s.getX() < head.getX() && s.getY() == head.getY())
                            ||(s.getX() == 1  && s.getY() < 7)))  return Direction.RIGHT;
                    else if (snake.stream().anyMatch(s -> (s.getX() > head.getX() && s.getY() == head.getY())
                            ||( s.getX() == 13 && s.getY() < 7))) return Direction.LEFT;
                }
            }
            else{
                if(snake.stream().anyMatch(s -> s.getY() == head.getY() && s.getX() < head.getX() - 1)) {
                    if(snake.stream().anyMatch(s -> s.getY() < head.getY() && s.getX() == head.getX() - 1))
                        if(snake.stream().anyMatch(s -> s.getY() > head.getY() && s.getX() == head.getX() - 1))
                            return Direction.RIGHT;
                }
                return Direction.LEFT;
            }

        }
        else {
            if(walls.stream().anyMatch(w -> (w.getX() == head.getX() + 1 && w.getY() == head.getY())
                    || (w.getX() == head.getX() - 1 && w.getY() == head.getY()))) {
                if(snakeDir == Direction.RIGHT){
                    if(snake.stream().anyMatch(s -> (s.getX() == 13 && s.getY() < head.getY()
                            || (s.getX() > 7 && s.getY() == 1)))) return Direction.UP;
                    else if (snake.stream().anyMatch(s -> (s.getX() == 13 && s.getY() > head.getY()
                            || (s.getX() > 7 && s.getY() == 13)))) return Direction.DOWN;
                }
                else{
                    if(snake.stream().anyMatch(s -> (s.getX() == 1 && s.getY() < head.getY()
                            || (s.getX() < 7 && s.getY() == 1)))) return Direction.UP;
                    else if (snake.stream().anyMatch(s -> (s.getX() == 1 && s.getY() > head.getY()
                            || (s.getX() < 7 && s.getY() == 13)))) return Direction.DOWN;
                }

            }
            else {
                if(snake.stream().anyMatch(s -> s.getX() == head.getX() && s.getY() < head.getY() - 1)){
                    if(snake.stream().anyMatch(s -> s.getX() < head.getX() && s.getY() == head.getY() - 1))
                        if(snake.stream().anyMatch(s-> s.getX() > head.getX() && s.getY() == head.getY() - 1))
                            return Direction.UP;
                }
                return Direction.DOWN;
            }
        }
        return correctDir;
    }
    public static Direction circleLoc(Board b){
        List<Point> snake = b.getSnake();
        Point head = b.getHead();
        Direction snakeDir = b.getSnakeDirection();
        List<Point> barries = b.getBarriers();
        List<Point> walls = b.getWalls();


        Direction correctDir = null;
        if(snakeDir == Direction.UP){
            if(barries.stream().anyMatch(s -> (s.getX() == head.getX() && s.getY() == head.getY() + 1))){
                correctDir = correctDir(b);
                System.out.println("Up Correctdir is " + correctDir);
            } else if (barries.stream().anyMatch(s -> (s.getX() == b.getHead().getX() + 1 && s.getY() == b.getHead().getY() + 1)
                    || (s.getX() == b.getHead().getX() - 1 && s.getY() == b.getHead().getY() + 1))) {

            }
        } else if (snakeDir == Direction.DOWN) {
            if(barries.stream().anyMatch(s -> (s.getX() == head.getX() && s.getY() == head.getY() - 1))){
                correctDir = correctDir(b);
                System.out.println("Down Correctdir is " + correctDir);
            }
        } else if (snakeDir == Direction.RIGHT){
            if(barries.stream().anyMatch(ba -> (ba.getY() == head.getY() && ba.getX() == head.getX() + 1))){
                correctDir = correctDir(b);
                System.out.println("Right Correctdir is " + correctDir);
            }
        }
        else if(snakeDir == Direction.LEFT) {
            if(barries.stream().anyMatch(s -> (s.getY() == head.getY() && s.getX() == head.getX() - 1))){
                correctDir = correctDir(b);
                System.out.println("Left Correctdir is " + correctDir);
            }
        }
        return correctDir;
    }

    public static Direction findLoc(Board b){
        Direction snakeDirection = b.getSnakeDirection();
        if(isEmpty(b, snakeDirection)) return snakeDirection;
        else if(isEmpty(b, Direction.UP)) return Direction.UP;
        else if(isEmpty(b, Direction.DOWN)) return Direction.DOWN;
        else if(isEmpty(b, Direction.LEFT)) return Direction.LEFT;
        else return Direction.RIGHT;
    }

    public static boolean isEmpty(Board b, Direction d){
        Point head = b.getHead();
        List<Point> barriers = b.getBarriers();


        if(d == Direction.UP){
           return barriers.stream().noneMatch(ba -> (ba.getY() == (head.getY() + 1) && ba.getX() == head.getX()));
        }
        else if(d == Direction.DOWN){
            return barriers.stream().noneMatch(ba -> (ba.getY() == (head.getY() - 1) && ba.getX() == head.getX()));
        }
        else if(d == Direction.RIGHT){
            return barriers.stream().noneMatch(ba -> (ba.getX() == (head.getX() + 1) && ba.getY() == head.getY()));
        }
        else return barriers.stream().noneMatch(ba -> (ba.getX() == (head.getX() - 1) && ba.getY() == head.getY()));
    }

    public static Direction solve(Board b){
        Direction validDir;
        Point apple = b.getApples().get(0);
        Point head = b.getHead();
        Point stone = b.getStones().get(0);
        if(b.isGameOver()){
            System.out.println("game over");
            return Direction.RIGHT;
        }


        if(noWay(b)){
            if(head.getX() > stone.getX()) validDir = Direction.LEFT;
            else if(head.getX() < stone.getX() && isEmpty(b, Direction.RIGHT)) validDir = Direction.RIGHT;
            else if(head.getY() > stone.getY()) validDir = Direction.DOWN;
            else validDir = Direction.UP;
            return validDir;
        }
        if(circleLoc(b) != null && isEmpty(b, circleLoc(b))) validDir = circleLoc(b);
        else if(head.getX() > apple.getX() && isEmpty(b, Direction.LEFT)){
            validDir =  Direction.LEFT;
        }
        else if(head.getX() < apple.getX() && isEmpty(b, Direction.RIGHT)){
            validDir = Direction.RIGHT;
        }
        else if(head.getY() > apple.getY() && isEmpty(b, Direction.DOWN)){
            validDir = Direction.DOWN;
        }
        else if(head.getY() < apple.getY() && isEmpty(b, Direction.UP)) {
            validDir = Direction.UP;
        }
        else{
            validDir = findLoc(b);
        }

        return validDir;
    }
}
