#include <iostream>

void removeCaptures(int *board, int boardSize)
{
    int *toKeep = new int[boardSize * boardSize];
    bool changed = true;
    for(int i = 0; i < boardSize; i++)
    {
        for(int j = 0; j < boardSize; j++)
        {
            toKeep[boardSize * i + j] = 0;
        }
    }
    for(int i = 0; i < boardSize; i++)
    {
        for(int j = 0; j < boardSize; j++)
        {
            if(board[boardSize * i + j] == 0)
            {
                toKeep[boardSize * i + j] = 1;
            }
        }
    }

    int temp;
    
    while(changed)
    {
        changed = false;
        for(int i = 0; i < boardSize; i++)
        {
            for(int j = 0; j < boardSize; j++)
            {
                if(toKeep[boardSize * i + j])
                {
                    if(board[boardSize * i + j] == 0)
                    {
                        if((i - 1) >= 0)
                        {
                            if(!toKeep[boardSize * (i - 1) + j]) changed = true;
                            toKeep[boardSize * (i - 1) + j] = 1;
                        }
                        if((j - 1) >= 0)
                        {
                            if(!toKeep[boardSize * i + j - 1]) changed = true;
                            toKeep[boardSize * i + j - 1] = 1;
                        }
                        if((i + 1) < boardSize)
                        {
                            if(!toKeep[boardSize * (i + 1) + j]) changed = true;
                            toKeep[boardSize * (i + 1) + j] = 1;
                        }
                        if((j + 1) < boardSize)
                        {
                            if(!toKeep[boardSize * i + j + 1]) changed = true;
                            toKeep[boardSize * i + j + 1] = 1;
                        }
                    }
                    else
                    {
                        temp = board[boardSize * i + j];
                        if(((i - 1) >= 0) && (board[boardSize * (i - 1) + j] == temp))
                        {
                            if(!toKeep[boardSize * (i - 1) + j]) changed = true;
                            toKeep[boardSize * (i - 1) + j] = 1;
                        }
                        if(((j - 1) >= 0) && (board[boardSize * i + j - 1] == temp))
                        {
                            if(!toKeep[boardSize * i + j - 1]) changed = true;
                            toKeep[boardSize * i + j - 1] = 1;
                        }
                        if(((i + 1) < boardSize) && (board[boardSize * (i + 1) + j] == temp))
                        {
                            if(!toKeep[boardSize * (i + 1) + j]) changed = true;
                            toKeep[boardSize * (i + 1) + j] = 1;
                        }
                        if(((j + 1) < boardSize) && (board[boardSize * i + j + 1] == temp))
                        {
                            if(!toKeep[boardSize * i + j + 1]) changed = true;
                            toKeep[boardSize * i + j + 1] = 1;
                        }
                    }
                }
            }
        }
    }

    for(int i = 0; i < boardSize; i++)
    {
        for(int j = 0; j < boardSize; j++)
        {
            if(!toKeep[boardSize * i + j])
            {
                board[boardSize * i + j] = 0;
            }
        }
    }

    delete[] toKeep;
}

int area(int *board, int boardSize, int color)
{
    int score = 0, opp = 1, temp;
    bool skip = false;

    if(color == opp) opp = 2;

    for(int i = 0; i < boardSize; i++)
    {
        for(int j = 0; j < boardSize; j++)
        {
            if(board[boardSize * i + j] == color)
            {
                score++;
            }
            else if(board[boardSize * i + j] == 0)
            {
                skip = false;
                
                temp = i;
                while(temp >= 0)
                {
                    if(board[boardSize * temp + j] == color)
                    {
                        break;
                    }
                    else if(board[boardSize * temp + j] == opp)
                    {
                        skip = true;
                        break;
                    }

                    temp--;
                }
                if(skip) continue;

                temp = i;
                while(temp < boardSize)
                {
                    if(board[boardSize * temp + j] == color)
                    {
                        break;
                    }
                    else if(board[boardSize * temp + j] == opp)
                    {
                        skip = true;
                        break;
                    }

                    temp++;
                }
                if(skip) continue;

                temp = j;
                while(temp >= 0)
                {
                    if(board[boardSize * i + temp] == color)
                    {
                        break;
                    }
                    else if(board[boardSize * i + temp] == opp)
                    {
                        skip = true;
                        break;
                    }

                    temp--;
                }
                if(skip) continue;

                temp = j;
                while(temp < boardSize)
                {
                    if(board[boardSize * i + temp] == color)
                    {
                        break;
                    }
                    else if(board[boardSize * i + temp] == opp)
                    {
                        skip = true;
                        break;
                    }

                    temp++;
                }
                if(skip) continue;

                score++;
            }
        }
    }

    return score;
}

int main()
{
    int boardSize;
    
    std::cin >> boardSize;

    int *board = new int[boardSize * boardSize];

    for(int i = 0; i < boardSize; i++)
    {
        for(int j = 0; j < boardSize; j++)
        {
            std::cin >> board[boardSize * i + j];
        }
    }

    removeCaptures(board, boardSize);
    int score = area(board, boardSize, 1);
    std::cout << "\n" << score << "\n";

    std::cout << "\n";

    for(int i = 0; i < boardSize; i++)
    {
        for(int j = 0; j < boardSize; j++)
        {
            std::cout << board[boardSize * i + j] << " ";
        }
        std::cout << "\n";
    }

    return 0;
}