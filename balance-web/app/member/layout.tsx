import React from "react";

export default function MemberLayout({ children } : { children : Readonly<React.ReactNode>} ) {
    return (
        <>
            { children }
        </>
    )
}