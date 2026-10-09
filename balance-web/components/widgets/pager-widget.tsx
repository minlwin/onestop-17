'use client'
import { PageInfo } from "@/lib/types";
import { Button } from "../ui/button";
import { ChevronLeft, ChevronRight } from "lucide-react";

export default function PagerWidget({pageInfo, onPageChange} : {
    pageInfo : PageInfo, 
    onPageChange : (page : number) => void
}) {

    if(pageInfo.totalPage <= 1) {
        return null
    }

    return (
        <nav className="flex justify-between items-stretch">
            <div className="flex gap-2">
                <CountWidget label="Total Pages" value={pageInfo.totalPage} />
                <CountWidget label="Total Count" value={pageInfo.totalCount} />
            </div>

            <div className="flex gap-1">
                <Button onClick={() => onPageChange(0)} variant={'outline'} className="w-10">
                    <ChevronLeft />
                </Button>

                {pageInfo.links.map(item => 
                    item === pageInfo.page ? 
                    <Button key={item} className="w-10">{item + 1}</Button> :
                    <Button key={item} variant={'outline'} onClick={() => onPageChange(item)} className="w-10">{item + 1}</Button>
                )}

                <Button onClick={() => onPageChange(pageInfo.totalPage - 1)} variant={'outline'} className="w-10">
                    <ChevronRight />
                </Button>
            </div>
        </nav>
    )
}

function CountWidget({label, value} : {label : string, value : number}) {
    return (
        <div className="flex border rounded-md h-full">
            <div className="border-r bg-gray-200 flex items-center px-2">{label}</div>
            <div className="px-4 flex items-center">{value}</div>
        </div>
    )
}